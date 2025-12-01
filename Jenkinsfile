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

                sh 'docker exec test-runner mvn -f /app/pom.xml test'
            }
        }

        stage('Generate coverage') {
            steps {
           
                sh 'docker exec test-runner mvn -f /app/pom.xml jacoco:report-xml'
            }
        }

        stage('Publish coverage') {
            steps {
                publishCoverage adapters: [jacocoAdapter('backend/target/site/jacoco/jacoco.xml')], 
                                sourceFileResolver: sourceFiles('NEVER_STORE')
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
