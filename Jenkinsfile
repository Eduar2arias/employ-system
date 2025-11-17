pipeline {
    agent any

    environment {
        DOCKER_HOST = 'unix:///var/run/docker.sock'
    }

    stages {

        stage('Construir imágenes') {
            steps {
                sh 'docker-compose build'
            }
        }

        // stage('Ejecutar pruebas backend') {
        //     steps {
        //         sh '''
        //             docker-compose run --rm backend \
        //             ./mvnw test || exit 1
        //         '''
        //     }
        // }

        // stage('Ejecutar pruebas frontend') {
        //     steps {
        //         sh '''
        //             docker-compose run --rm frontend \
        //             npm test || echo "No test configured"
        //         '''
        //     }
        // }

        stage('Desplegar') {
            steps {
                sh 'docker-compose up -d'
            }
        }
    }

    post {
        always {
            sh 'docker compose ps'
        }
    }
}
