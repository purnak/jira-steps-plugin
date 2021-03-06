package org.thoughtslive.jenkins.plugins.jira.steps;

import java.io.IOException;

import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.StepExecution;
import org.kohsuke.stapler.DataBoundConstructor;
import org.thoughtslive.jenkins.plugins.jira.api.IssueLinkTypes;
import org.thoughtslive.jenkins.plugins.jira.api.ResponseData;
import org.thoughtslive.jenkins.plugins.jira.util.JiraStepDescriptorImpl;
import org.thoughtslive.jenkins.plugins.jira.util.JiraStepExecution;

import hudson.Extension;

/**
 * Step to query a JIRA Issue linkss types so that we can link issues later.
 *
 * @author Naresh Rayapati
 */
public class GetIssueLinkTypesStep extends BasicJiraStep {

  private static final long serialVersionUID = 7300279362207875286L;

  @DataBoundConstructor
  public GetIssueLinkTypesStep() {}

  @Extension
  public static class DescriptorImpl extends JiraStepDescriptorImpl {

    @Override
    public String getFunctionName() {
      return "jiraGetIssueLinkTypes";
    }

    @Override
    public String getDisplayName() {
      return getPrefix() + "Get Issue Link Types";
    }

  }

  public static class Execution extends JiraStepExecution<ResponseData<IssueLinkTypes>> {

    private static final long serialVersionUID = -1387617043703686867L;

    private final GetIssueLinkTypesStep step;

    protected Execution(final GetIssueLinkTypesStep step, final StepContext context)
        throws IOException, InterruptedException {
      super(context);
      this.step = step;
    }

    @Override
    protected ResponseData<IssueLinkTypes> run() throws Exception {

      ResponseData<IssueLinkTypes> response = verifyInput();

      if (response == null) {
        logger.println("JIRA: Site - " + siteName + " - Querying All Issue Link Types");
        response = jiraService.getIssueLinkTypes();
      }

      return logResponse(response);
    }

    @Override
    protected <T> ResponseData<T> verifyInput() throws Exception {
      return verifyCommon(step);
    }
  }

  @Override
  public StepExecution start(StepContext context) throws Exception {
    return new Execution(this, context);
  }
}
