# AndroidApplication-For-Zillow
Android App for Property Search with Facebook posts

1. Objectives
● Become familiar with Eclipse, Android and Facebook
● Use Java code with a combination of the Android SDK and Facebook SDK
● Provide an interface to post text and pictures to Facebook.

Implementation:
1. Initial User Interface: Provide an Android Activity (page) to take Address, City, and State as input. The Address and City fields are text boxes, while the “State” field is a dropdown menu selector (called Spinner in Android). The fields should all be marked as compulsory (*) and appropriate text hints should be provided in the textbox.
2. When the “Search” button is tapped, error checking is done, and on valid input, the JSON result from the PHP file located on your AWS server is returned. On all valid input, another activity should show the results page, which is a tabbed interface containing Basic Info Tab and Historical Zestimates Tab.
3. The Basic Info Tab:
This should have a scrollable list detailing all the information about the property.The footer with terms of service, copyright notice and “What‟s a Zestimate link” should always be visible. In other words, only the info section should scroll, not the whole page
4. Facebook: There should be a button at the top-right of the “Basic Info” tab which is a Facebook share button. The pop up window that first appears should also have an option to cancel the action, along with posting on Facebook. The information posted to Facebook is -The Address, with details of its last sold price and 30 days overall change, with any of the corresponding chart as the image. On successful posting (or failure), appropriate short “Toast” messages should be shown at the bottom ("Posted Story, ID: XXXXX.." for successful post or "Post Cancelled"). You may also use pop-up windows/dialog boxes to show the same.
5. Historical Zestimates Tab:
The second and only other tab must show the images of the Zestimate charts. You may use ImageSwitcher and TextSwitcher implementation to make only one chart image at a time and corresponding text label to be shown. Clicking on NEXT or PREVIOUS buttons should switch to the appropriate charts.


Error Handling:
Empty Fields: If any of the text boxes are empty, or the State is not selected, “This field is required” or similar text should be posted below the box in red or other warning color as shown. The current inputs (such as the filled address in the example below) must be maintained in all such cases.

Invalid Input: If any of the values are invalid, or there were no matches found by the Zillow API (PHP), then input values must be retained, but the error message should be shown at the bottom as shown in the figure to the right below.
The results page must be shown only on valid JSON results.

