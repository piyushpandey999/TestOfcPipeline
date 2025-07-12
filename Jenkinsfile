pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/piyushpandey999/APIAutomation.git'
            }
        }

        stage('Test') {
            steps {
            echo "Domain is : ${params.Domain}"
            echo "Domain Type is : ${params.domain_type}"
            echo "Service is : ${params.ServiceName}"
            echo "IP Port is : ${params.IP_Port}"


                script {
                    // Use bat for Windows or sh for Unix/Linux
                    if (isUnix()) {
                        sh "mvn clean test -Dmaven.repo.local=./local-maven-repo -o -Dtest=${ServiceName} -Dips=${IP_Port}"
                    } else {
                        bat "mvn clean test -Dmaven.repo.local=./local-maven-repo -o -Dtest=${ServiceName} -Dips=${IP_Port}"
                    }
                }
            }
        }
    }
}
