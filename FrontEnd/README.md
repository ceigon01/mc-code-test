 # MasteryConnect Coding Test #

---

### Part I - Form creation and styling

Create a registration form that would be production-ready. The form should have the following fields:

* First Name
* Last Name
* Phone
* Email
* Short Bio (textarea with 1000 characters maximum) 

The form should be responsive and have two primary modes: browser and mobile.  Use css to accomplish the responsive behavior.

Wireframes are provided in the /wireframes directory (we're all about creative directory naming...).  The dotted lines are meant to indicate alignment.

Place all of your HTML in the index.html and all your CSS inside styles.css.  We have included normalize.css file (https://necolas.github.io/normalize.css/) in the css directory as well.  This will help standardize css, but it is not required that you use it.  Please do not use any other external css libraries (bootstrap, material UI, etc).

Do not worry about making any links work, or form(s) posting.  You are encouraged to make the form visually appealing with intuative UI, error states etc. That being said, you are not being judged on your design sensabilities- we're more concerned that your work meets the criteria layed out and that you pay attention to detail, not whether you're a rockstar designer (although if you are, we also have UI/UX positions available).


### Part II - Form validation

Use javascript to validate the fields.  Please do not use any frameworks (react, angular, etc), however, you may use jQuery if you would like.

All fields except for phone are required.

You should also vaildate that the email address is not already taken.  You can do this by using an API:

- **Endpoint:** http://codetest.socrative.com/v1/api/email-check/(email to test)/
- **Method:** GET
- **Success Response** 200 OK
- **Error Response** 409 Conflict (email already exists); 400 Bad Request (invalid email)

Since this is a static API, the values won't change.  For testing, the following email addresses will cause a conflict: a@a.com, test@masteryconnect.com


### Part III - Backend

You have the option to use one of the following languages for this part: 

* Ruby
* Python
* JavaScript
* Go
* Java
* C++

In a separate file, create a class (or it's equivalent in the language you chose). The class should contain each of the following methods:


##### Letter Counting
This method accepts a string as a param, and returns an ordered array, hash, dictionary (depending on language).

The method will return each unique letter (case-insensitive) the string contains in order from most frequent to least frequent.

eg. if the input string is 'foo bar baz', the return for a ruby method should be: {a=>2, b=>2, o=>2, f=>1, r=>1, z=>1}. The detail of the return method will vary based on language but should maintain the order and count of each letter.


##### Divisors
The second method takes an integer and returns an array with all of the integer's divisors(except for 1 and the number itself). In weakly typed languages (Ruby, Python, Javascript), if the number is prime return the string '(integer) is prime'.  In strongly typed languages (Go, Java, C++), if the number is prime, it should return an empty array.

Example:

* divisors(12); should return [2,3,4,6]
* divisors(25); should return [5]
* divisors(13); should return "13 is prime" or []

You can assume that you will only get positive integers as inputs.


##### Array Sorting
The third method requires you to retrieve an array from an API, remove duplicates (if any), and sort the output alphabetically. The API that you will call is:

- **Endpoint:** http://codetest.socrative.com/v1/api/array/
- **Method:** GET
- **Success Response** 200 OK; Response JSON: {data: [ (array of strings )]}
- **Error Response** 400 Bad Request

If successful, the API will return a JSON object with a single attribute: data.  The value of the data attribute will be an array of strings.  The length of the array will vary, but will never exceed 20.

The API will occationally return an error.  If this happens, the method should output: "An unexpected error occurred".

__Success Example:__

If the API returns `{"data": ["parrot", "Cat", "dog", "Cat", "Duckbill platypus"]}`

Output (bullets not required):

* Cat
* dog
* Duckbill platypus
* parrot

__Bonus:__ Determine the error rate of the API.

---

You should be working in your own branch - generate commits at logical stop points. When you are finished, create a pull request and we will review your work. Do not push anything to master.

please don't hesitate to email **mwest@masteryconnect.com**, **josh@masteryconnect.com**, **rick@masteryconnect.com** if you have any questions at all.