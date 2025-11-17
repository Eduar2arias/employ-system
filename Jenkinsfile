pipeline {
    agent any

    environment {
        DOCKER_HOST = 'unix:///var/run/docker.sock'
    }

    stages {

        stage('Construir imágenes') {
            steps {
                sh 'docker compose build'
            }
        }


        stage('Desplegar') {
            steps {
                sh 'docker compose up -d'
                // sh 'docker compose run --rm backend ./mvnw test'
            }
        }
    }

    post {
        always {
            sh 'docker compose ps'
        }
    }
}
