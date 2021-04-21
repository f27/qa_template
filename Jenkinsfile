def allureFile = 'allure-notifications.jar'
def allureNotificationsUrl = 'https://github.com/qa-guru/allure-notifications/releases/download/fr/allure-notifications-2.2.3.jar'
def allureTestOpsProjectId = '164'

pipeline {
    def GIT_URL = GIT_URL.replaceAll("_",'\\\\_')
    agent any
    tools {
        gradle "Gradle 6.8.3"
    }
    triggers {
        githubPush()
    }
    parameters {
        string(name: 'THREADS', defaultValue: '2', trim: true)
        choice(name: 'TASK', choices: ['test', 'selenide', 'jsoup', 'api'], description: 'test - run all tests\nselenide - run tests with selenide\njsoup - run tests with jsoup parser')
        choice(name: 'BROWSER', choices: ['chrome', 'opera', 'firefox'], description: '')
        string(name: 'BROWSER_VERSION', defaultValue: '89', trim: true)
        choice(name: 'BROWSER_SIZE', choices: ['1366x768', '1920x1080', '1024x768'], description: '')
        choice(name: 'BROWSER_MOBILE_VIEW', choices: ['', 'iPhone X', 'iPad Pro', 'iPad', 'Pixel 2', 'Surface Duo'], description: 'Leave empty for desktop')
        string(name: 'REMOTE_WEB_DRIVER_DOMAIN', defaultValue: 'selenoid.autotests.cloud', trim: true)
        string(name: 'REMOTE_WEB_DRIVER_URL_FORMAT', defaultValue: 'https://%s:%s@%s/wd/hub/', trim: true)
        credentials(name: 'REMOTE_WEB_DRIVER_CRED_ID',
                description: 'Username and password for remote web driver',
                defaultValue: '1933267a-4824-4f65-9bfe-d8a47445ee39',
                credentialType: "usernamePassword",
                required: true)
        string(name: 'VIDEO_STORAGE_FORMAT', defaultValue: 'https://%s/video/', trim: true)
        string(name: 'TELEGRAM_CHAT_ID', defaultValue: '-548005165')
        credentials(name: 'TELEGRAM_BOT_TOKEN_ID',
                description: 'Telegram bot token for sending notifications in telegram chat',
                defaultValue: 'c05-fattaft-telegram-token',
                credentialType: "jenkins_secret_text_credentials",
                required: true)
    }
    stages {
        stage('Test') {
            steps {
                withAllureUpload(name: '${JOB_NAME} - #${BUILD_NUMBER}', projectId: allureTestOpsProjectId, results: [[path: 'build/allure-results']], serverId: 'allure-server', tags: 'tags') {
                    withCredentials([
                            usernamePassword(credentialsId: '${REMOTE_WEB_DRIVER_CRED_ID}', usernameVariable: 'REMOTE_WEB_DRIVER_USERNAME', passwordVariable: 'REMOTE_WEB_DRIVER_PASSWORD')
                    ]) {
                        sh 'gradle clean ${TASK}' +
                                ' -Dthreads="${THREADS}"' +
                                ' -Dweb.browser="${BROWSER}"' +
                                ' -Dweb.browser.version="${BROWSER_VERSION}"' +
                                ' -Dweb.browser.size="${BROWSER_SIZE}"' +
                                ' -Dweb.browser.mobile.view="${BROWSER_MOBILE_VIEW}"' +
                                ' -Dweb.remote.driver.domain="${REMOTE_WEB_DRIVER_DOMAIN}"' +
                                ' -Dweb.remote.driver.url.format="${REMOTE_WEB_DRIVER_URL_FORMAT}"' +
                                ' -Dweb.remote.driver.user="${REMOTE_WEB_DRIVER_USERNAME}"' +
                                ' -Dweb.remote.driver.password="${REMOTE_WEB_DRIVER_PASSWORD}"' +
                                ' -Dvideo.storage.format="${VIDEO_STORAGE_FORMAT}"'
                    }
                }
            }
        }
    }

    post {
        always {
            allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
            withCredentials([string(credentialsId: '${TELEGRAM_BOT_TOKEN_ID}', variable: 'TELEGRAM_BOT_TOKEN')]) {
                sh "if [ ! -f '${allureFile}' ]; then wget -O '${allureFile}' '${allureNotificationsUrl}'; fi"
                sh "java" +
                        " -Dmessenger='telegram'" +
                        " -Dchat.id='${TELEGRAM_CHAT_ID}'" +
                        " -Dbot.token='${TELEGRAM_BOT_TOKEN}'" +
                        " -Dbuild.launch.name='${JOB_NAME} - #${BUILD_NUMBER}'" +
                        " -Dbuild.env='${GIT_URL}'" +
                        " -Dbuild.report.link='${BUILD_URL}'" +
                        " -Dproject.name='${JOB_BASE_NAME}'" +
                        " -Dlang='ru'" +
                        " -Denable.chart='true'" +
                        " -Dallure.report.folder='./allure-report/'" +
                        " -jar ${allureFile}"
            }
        }
    }
}

