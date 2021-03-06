---
title: jiraNotifyIssue
tags: [steps]
keywords: steps, issue, email, notify
summary: "More about jiraNotifyIssue step."
sidebar: jira_sidebar
permalink: jira_notify_issue.html
folder: steps
---

## Overview

Notify users (like watchers, assignee and so on..) of issue.

## Fields

* **idOrKey** - Issue id or key.
* **notify** - more info about whom should we notify and so on.
* **site** - Optional, default: `JIRA_SITE` environment variable.
* **failOnError** - Optional. default: `true`.

## Examples

* With default [site](config#environment-variables) from global variables.

  ```groovy
  node {
    stage('JIRA') {
      def notify = [ subject: "Update about TEST-01",
                     textBody: "Just wanted to update about this issue...",
                     htmlBody: "Just wanted to update about this issue...",
                     to: [ reporter: true,
                           assignee: true,
                           watchers: false,
                           voters: false,
                           users: [{
                                    "name": "rao",
                                  },
                                  {
                                    "name": "naresh",
                                  }]
                        ]
                    ]
      jiraNotifyIssue idOrKey: "TEST-1", notify: notify
    }
  }
  ```
* `withEnv` to override the default site (or if there is not global site)

  ```groovy
  node {
    stage('JIRA') {
      withEnv(['JIRA_SITE=LOCAL']) {
        def notify = [ subject: "Update about TEST-01",
                       textBody: "Just wanted to update about this issue...",
                       htmlBody: "Just wanted to update about this issue...",
                       to: [ reporter: true,
                             assignee: true,
                             watchers: false,
                             voters: false,
                             users: [{
                                      "name": "rao",
                                    },
                                    {
                                      "name": "naresh",
                                    }]
                          ]
                      ]
        jiraNotifyIssue idOrKey: "TEST-1", notify: notify
      }
    }
  }
  ```
* Without environment variables.

  ```groovy
    def notify = [ subject: "Update about TEST-01",
                   textBody: "Just wanted to update about this issue...",
                   htmlBody: "Just wanted to update about this issue...",
                   to: [ reporter: true,
                         assignee: true,
                         watchers: false,
                         voters: false,
                         users: [{
                                  "name": "rao",
                                },
                                {
                                  "name": "naresh",
                                }]
                       ]
                  ]
    jiraNotifyIssue idOrKey: "TEST-1", notify: notify, site: "LOCAL"
  ```

{% include links.html %}
