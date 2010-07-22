require 'java'
require 'build/jar/bvira-core.jar'
require 'build/jar/bvira-sample.jar'
require 'build/jar/bvira-sample-test.jar'

env = Java::BviraTest::WorkflowTestWebEnvironment.new
# Java::BviraTest::WebEnvironment.setInstance(env)
env.start()

Given /^the home page is displayed$/ do
  env.navigate(Java::BviraTestAbstraction::To.homePage())
end

When /^I click on the "([^\"]*)" link$/ do |link_name|
  env.clickLink(link_name)
end

Then /^the "([^\"]*)" page is displayed$/ do |page_title|
  page = env.responsePage(Java::BviraTestAbstraction::LoginPage)

  result = page_title == page.getTitle

  env.stop
  result
end
