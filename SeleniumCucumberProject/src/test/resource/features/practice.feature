#Author:Abhijit A Nikam
#Date : 26-11-2024
Feature: Login to practice page

  @Download_File
  Scenario Outline: Verify practise page componant
    Given Open practice page site "https://rahulshettyacademy.com/AutomationPractice/"
    When Select testdata from excel "<SheetName>" <rownum>
    And Check for radio button
    And Select suggetion country name
    And Select Dropdown example
    And Select check box
    #And Check new window is open or not
    #And Check new tab is open or not
    And Check alert is handle
    And Find course amount from table
    And Enter value in hide field
    And Mousehover on page
    And Switch to frame

    Examples: 
      | SheetName | rownum |
      | Sheet1    |      0 |
