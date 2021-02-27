# Qualitest
A Web test solution that automates below simple tests, composed as BDD scenarios. 
The target for the test is the dummy web site: http://automationpractice.com

Scenario 1: Order T-Shirt (and Verify in Order History)
Scenario 2: Update Personal Information (First Name) in My Account

Technology Stack:
                Java,
                Selenium,
                Cucumber

Execution Instructions

    PreRequisite:
        Chrome Browser 87+

    Run steps:
        Step 1: Import the project to any IDE
        Step 2: Run the feature file available in src/main/resources/features/EcommerceTestPack.feature

Further Improvement:

    Input File Handling: 
        Additional interfaces to be created for handling External input files (Excel, json, xml, etc.,)
    Exception Handling:
        A better custom made exceptions has to be created for better exception handling
    Reusablility:
        More reusable methods to be created to handle common actions along with possible error capturing
        techniques making standard and predefined rules for all the test developers
    Scalability:
        More Interfaces can be provided for webservices calls, Database integration, external system integrations
    Reporting:
        Most importantly reporting should be in much more better way to make it more of a
        good framework, currently it is missing. That can e achieved by adding Excel report,XML, HTML 
        report generators in the framework along with screenshots attached against test steps.

Additional Solution:
    This can be made to an executable jar rather than IDE based executor.