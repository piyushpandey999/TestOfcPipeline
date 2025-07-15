pipeline {
    agent any
      environment {
              REPORT_PATH = 'Reports/TestReport.html' // Use the correct file name
          }
    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout') {
            steps {
                git 'https://github.com/piyushpandey999/TestOfcPipeline.git'
                script {
                    if (isUnix()) {
                        sh 'cat pom.xml'
                        sh 'ls -R local-maven-repo/org/apache/maven/plugins || true'
                    } else {
                        bat 'type pom.xml'
                        bat 'dir local-maven-repo\\org\\apache\\maven\\plugins /s || exit 0'
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
                        sh 'ls -l /home/esb_user/.jenkins/workspace/Test_Automation_Pipeline/Reports/TestReport.html || echo "Test report not found!"'
                    } else {
                        bat 'mvn help:evaluate -Dexpression=settings.localRepository -Dmaven.repo.local=.\\local-maven-repo -o'
                        bat "mvn clean test -Dmaven.repo.local=.\\local-maven-repo -o -Dtest=${params.ServiceName} -Dips=${params.IP_Port} -X"
                        bat 'dir /home/esb_user/.jenkins/workspace/Test_Automation_Pipeline/Reports/TestReport.html || echo "Test report not found!"'
                    }
                }
            }
        }

        stage('E-Mail Test Results') {
            steps {
                echo "JOB_NAME and BUILD_NUMBER is - ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}"
                echo "To_Email_Address is - ${env.To_Email_Address}"

                script {
                                    if (fileExists(env.REPORT_PATH)) {
                                        echo "Test report found at ${env.REPORT_PATH}"
                                    } else {
                                        error "Test report not found at ${env.REPORT_PATH}. Cannot proceed."
                                    }
            }
        }
    }

    post {
        always {
            script {
                def reportPath = isUnix() ? '/home/esb_user/.jenkins/workspace/Test_Automation_Pipeline/Reports/TestReport.html' : 'Reports\\TestReport.html'
                def fileExists = fileExists(reportPath)
                if (!fileExists) {
                    error "Test report not found at ${reportPath}. Cannot send email with attachment."
                }
                emailext (
                    subject: "Test Results for ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                    body: """
                        <p>Dear Team,</p>
                        <p>Please find the test results for the job <b>${env.JOB_NAME}</b>, Build #${env.BUILD_NUMBER}.</p>
                        <p>Status: ${currentBuild.currentResult}</p>
                        <p>Check the attached report for details.</p>
                        <p>Build URL: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                        <p>Best regards,<br>Jenkins</p>
                    """,
                    to: "${env.To_Email_Address}",
                    attachmentsPattern: "${env.REPORT_PATH}" // Use GLOB pattern,
                    mimeType: 'text/html'
                )
            }
        }
    }
}