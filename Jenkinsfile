pipeline {
  environment {
    registry = "yoksar/car-service"
    registryCredential = 'dockerhub'
    dockerImage = ''
    gitUrl = 'https://github.com/proftaak-s6/car-service'
  }
  agent any
    stages {
        stage('Cloning Git') {
            steps {
                git([url: gitUrl, branch: 'master', credentialsId: 'Github'])
            }
        }
        stage('Building image') {
            steps{
                script {
                dockerImage = docker.build registry
                }
            }
        }
        stage('Deploy Image') {
            steps{
                    script {
                        docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Remove Unused docker image') {
            steps{
                sh "docker rmi $registry"
            }
        }


        stage("Production") {
            steps{
                node("docker-prod"){
                    git([url: gitUrl, branch: 'master', credentialsId: 'Github'])
                    // sh "docker service rm uptime-portal_overheidsportaal"
                    sh "docker stack deploy --with-registry-auth -c docker-compose.prod.yml car-service"
                }
            }
        }
    }
}