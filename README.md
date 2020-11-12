# natera_test

## Running tests

- To run a test suite, navigate to '{projectDirectory}/natera_test/triangle-service-test' directory,
    and execute 'mvnw test' command in the command prompt.
- Normally, run will take 2-3 minutes to complete
- Report could be accessed via '/target/surefire-reports/index.html'

## Notes

 - All tests run in a single thread, ordered, because of maximum saved triangle amount.

## Bugs

- POST /triangle allows saving 11 triangles (only up to of 10 allowed)
- POST /triangle accept inputs with zero side length values
- POST /triangle returns 500 status (an NPE) when input field is null
- POST /triangle accept inputs with negative side length values
- POST /triangle accept inputs with impossible triangle side values (sum of two sides equals to third)
- POST /triangle accept inputs with values that will lead to Double type overflow 
    (e.g Double.MAX_VALUE, which leads to infinite area and perimeter values)

## Recommendations

- POST request should return 201 status on successful object creation
- DELETE request for missing id returns 200 status code (should be 204, since there is no response entity)