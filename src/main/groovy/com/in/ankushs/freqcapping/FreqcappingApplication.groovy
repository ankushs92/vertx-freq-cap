package com.in.ankushs.freqcapping

import com.in.ankushs.freqcapping.verticle.FreqCapVerticle
import groovy.util.logging.Slf4j
import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import javax.annotation.PostConstruct

@SpringBootApplication
@Slf4j
class FreqcappingApplication {

	@Autowired
	Vertx vertx

	@Autowired
	SpringVerticleFactory springVerticleFactory

	static void main(String[] args) {
		SpringApplication.run FreqcappingApplication, args
	}


	@PostConstruct
	void deployVerticles(){
		vertx.registerVerticleFactory(springVerticleFactory)
		def cores = Runtime.getRuntime().availableProcessors()
		log.info "Number of cores available {} " , cores
		def numOfVerticlesToDeploy = cores * 2
		log.info "Deploying {} verticle instances ", numOfVerticlesToDeploy
		def options = new DeploymentOptions().setInstances(numOfVerticlesToDeploy)
		vertx.deployVerticle(springVerticleFactory.prefix() + ":" + FreqCapVerticle.class.getName(),options,{ deployment->
			if(deployment.succeeded()){
				log.info "Deployment done. Info {} " , deployment.result()
			}
			else{
				log.error "Deployment Failed with exception ", deployment.cause()
			}
		})
	}
}
