
Feature: Complaint

  Rule: A complaint must be longer than 10 characters long and a complaint must not contain words such as "fuck", "frick", "shit", "ass".

    Scenario: A good complaint
      Given this complaint "this is a good complaint" is submitted
      And the complaint is checked by the complaintLogic method and is valid
      When the complaint has been fixed
      Then the submitter receives an email
