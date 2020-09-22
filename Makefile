SENTRY_ORG=testorg-az
SENTRY_PROJECT=ido-react-native-hc
VERSION=com.sentry_react_native@1.0+1
PREFIX=static/js
ENVIRONMENT=Production

setup_android: assemble_android build_react_native_android create_release associate_commits deploy_release

# setup_release: build_react_native_android create_release associate_commits deploy_release
assemble_android:
	cd android && ./gradlew assembleRelease

build_react_native_android:
	npx react-native run-android --variant Release

create_release:
	sentry-cli releases -o $(SENTRY_ORG) new -p $(SENTRY_PROJECT) $(VERSION)

associate_commits:
	sentry-cli releases -o $(SENTRY_ORG) -p $(SENTRY_PROJECT) set-commits --auto $(VERSION)
	# sentry-cli releases -o $(SENTRY_ORG) -p $(SENTRY_PROJECT) set-commits --commit "idosun/react-demo-app@f92fab9a247cf6b08afdcd7a2a3f2edc6f8c4739..ea21dfbfddcc1df99916fe8c979c136cf374193c" $(VERSION)

deploy_release:
	sentry-cli releases -o $(SENTRY_ORG) deploys $(VERSION) new -e $(ENVIRONMENT)
