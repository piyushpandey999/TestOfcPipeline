pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/piyushpandey999/APIAutomation.git'
                script {
                    if (isUnix()) {
                        sh 'cat pom.xml'
                        sh 'ls -R local-maven-repo/org/apache/maven/plugins/maven-clean-plugin'
                    } else {
                        bat 'type pom.xml'
                        bat 'dir local-maven-repo\\org\\apache\\maven\\plugins\\maven-clean-plugin /s'
                    }
                }
            }
        }

        stage('Test') {
            steps {
                echo "Domain is : ${params.Domain}"
                echo "Domain Type is : ${params.domain_type}"
                echo "Service is : ${params.ServiceName}"
                echo "IP Port is : ${params.IP_Port}"
                script {
                    if (isUnix()) {
                        sh 'mvn help:evaluate -Dexpression=settings.localRepository -Dmaven.repo.local=./local-maven-repo -o'
                        sh "mvn clean test -Dmaven.repo.local=./local-maven-repo -o -Dtest=${params.ServiceName} -Dips=${params.IP_Port} -X"
                    } else {
                        bat 'mvn help:evaluate -Dexpression=settings.localRepository -Dmaven.repo.local=.\\local-maven-repo -o'
                        bat "mvn clean test -Dmaven.repo.local=.\\local-maven-repo -o -Dtest=${params.ServiceName} -Dips=${params.IP_Port} -X"
                    }
                }
            }
        }
    }
}