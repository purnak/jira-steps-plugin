---
title: jiraNewIssue
tags: [steps]
keywords: steps, issue
summary: "More about jiraNewIssue step."
sidebar: jira_sidebar
permalink: jira_new_issue.html
folder: steps
---

## Overview

Creates new issue based on given input, which should have some minimal information on that object.

![New Issue](https://raw.githubusercontent.com/ThoughtsLive/jira-steps/master/docs/images/jira_new_issue.png)

## Fields

* **issue** - issue to be created.
* **site** - Optional, default: `JIRA_SITE` environment variable.
* **failOnError** - Optional. default: `true`.

## Examples

* With default [site](config#environment-variables) from global variables.

  ```groovy
  node {
    stage('JIRA') {
      # Look at IssueInput class for more information.
      def testIssue = [fields: [ project: [id: '10000'],
                           summary: "New JIRA Created from Jenkins.",
                           description: "New JIRA Created from Jenkins.",
                           issuetype: [id: 3]]]

      response = jiraNewIssue issue: testIssue

      echo response.successful.toString()
      echo response.data.toString()
    }
  }
  ```
* `withEnv` to override the default site (or if there is not global site)

  ```groovy
  node {
    stage('JIRA') {
      withEnv(['JIRA_SITE=LOCAL']) {
        def testIssue = [fields: [ project: [id: '10000'],
                             summary: "New JIRA Created from Jenkins.",
                             description: "New JIRA Created from Jenkins.",
                             issuetype: [id: 3]]]

        response = jiraNewIssue issue: testIssue

        echo response.successful.toString()
        echo response.data.toString()
      }
    }
  }
  ```
* Without environment variables.

  ```groovy
  def testIssue = [fields: [ project: [id: '10000'],
                       summary: "New JIRA Created from Jenkins.",
                       description: "New JIRA Created from Jenkins.",
                       issuetype: [id: 3]]]

  response = jiraNewIssue issue: testIssue, site: "LOCAL"

  echo response.successful.toString()
  echo response.data.toString()
  ```

{% include links.html %}
