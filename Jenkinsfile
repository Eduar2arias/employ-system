pipeline {
    agent any

    environment {
        DOCKER_HOST = 'unix:///var/run/docker.sock'
        WORKSPACE_DIR = '/var/jenkins_home/workspace/Pipeline-Test-Backend'
    }

    stages {
        stage('Build images') {
            steps {
                sh 'docker compose build'
            }
        }

        stage('Deploy services') {
            steps {
                sh '''
                    docker compose down || true
                    docker compose up -d mysql backend tests
                '''
            }
        }

        stage('Run tests') {
            steps {
                // Ejecuta los tests
                sh 'docker exec test-runner mvn -f /app/pom.xml test'
            }
        }

        stage('Generate coverage') {
            steps {
                // Genera el reporte de JaCoCo dentro del contenedor
                sh 'docker exec test-runner mvn -f /app/pom.xml jacoco:report'

                // Copia los reportes al workspace de Jenkins
                sh 'docker cp test-runner:/app/target/site/jacoco .'
            }
        }

        stage('Publish coverage') {
            steps {
                // Publica el HTML generado con el plugin HTML Publisher
                publishHTML(target: [
                    reportDir: 'jacoco',
                    reportFiles: 'index.html',
                    reportName: 'Coverage Report'
                ])
            }
        }

        stage('Deploy frontend') {
            steps {
                sh 'docker compose up -d frontend'
            }
        }
    }

    post {
        always {
            sh 'docker compose down'
            sh 'docker compose ps'
        }
    }
}
