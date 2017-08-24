'use strict';

const yeoman = require('yeoman-generator');
const mkdirp = require('mkdirp');
const yosay = require('yosay');
const chalk = require('chalk');


module.exports = yeoman.Base.extend({
  initializing: function () {
    this.props = {};
  },
  prompting: function () {
    this.log(yosay(
      'Welcome to ' + chalk.red('Android Architecture Components with MVVM') + ' generator!'
    ));
    const prompts = [{
      name: 'name',
      message: 'What are you calling your app?',
      store: true,
      default: this.appname,
      validate: function (input) {
        if (/^([a-zA-Z0-9_]*)$/.test(input)) {
          return true;
        }
        return 'Your application name cannot contain special characters or a blank space, using the default name instead : ' + this.appname;
      }
    },
      {
        name: 'package',
        message: 'What package will you be publishing the app under?',
        validate: function (input) {
          if (/^([a-z_]{1}[a-z0-9_]*(\.[a-z_]{1}[a-z0-9_]*)*)$/.test(input)) {
            return true;
          }
          return 'The package name you have provided is not a valid Java package name.';
        },
        default: 'in.yourname.sample',
        store: true
      },
      {
        name: 'targetSdk',
        message: 'What Android SDK will you be targeting?',
        store: true,
        default: 25  // Android 7.0 (Naugat)
      },
      {
        name: 'minSdk',
        message: 'What is the minimum Android SDK you wish to support?',
        store: true,
        default: 15  // Android 4.0 (Ice Cream Sandwich)
      }];

    return this.prompt(prompts).then(props => {
      this.props.appPackage = props.package;
      this.appName = props.name;
      this.appPackage = props.package;
      this.androidTargetSdkVersion = props.targetSdk;
      this.androidMinSdkVersion = props.minSdk;
    });
  },

  writing: function () {
    var packageDir = this.props.appPackage.replace(/\./g, '/');
    mkdirp('app');
    mkdirp('app/src/androidTest/java/' + packageDir);
    mkdirp('app/src/main/res');
    mkdirp('app/src/main/java/' + packageDir);
    mkdirp('app/src/test/java/' + packageDir);
    mkdirp('app/src/test/java/' + packageDir);

    this.directory('gradle', 'gradle');
    this.directory('app/src/main/res', 'app/src/main/res');
    this.directory('app/keystore', 'app/keystore');

    // this.copy('gitignore', '.gitignore');
    this.copy('build.gradle', 'build.gradle');
    this.copy('gradle.properties', 'gradle.properties');
    this.copy('gradlew', 'gradlew');
    this.copy('gradlew.bat', 'gradlew.bat');
    this.copy('settings.gradle', 'settings.gradle');
    // this.copy('app/gitignore', 'app/.gitignore');
    this.copy('app/proguard-rules.pro', 'app/proguard-rules.pro');

    this.template('app/build.gradle', 'app/build.gradle');
    this.template('app/src/androidTest/java/me/ashif/sampleapp', 'app/src/androidTest/java/' + packageDir, this, {});
    this.template('app/src/main/AndroidManifest.xml', 'app/src/main/AndroidManifest.xml');
    this.template('app/src/main/java/me/ashif/sampleapp', 'app/src/main/java/' + packageDir, this, {});
    this.template('app/src/main/res/layout', 'app/src/main/res/layout', this, {});
    this.template('app/src/test/java/me/ashif/sampleapp', 'app/src/test/java/' + packageDir, this, {});
  }
});
