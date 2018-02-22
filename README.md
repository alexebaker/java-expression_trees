# Expression Trees

CS 554 spike 2 - Expression Trees


## Contact Information

    Author: Alexander Baker
    Email: alexebaker@unm.edu
    Date: 02/19/2018


## Usage

Build the project:

```bash
make jar
```

Run the project:

```bash
java -jar spike2.jar tests/t11.expi
```


## Specification Issues

### s2.5.3.1.2 Error Recovery

The spec does not state what to do with valid read input before the
error was found. Based on the file `test/e10.expo`, the valid read input
should be printed before error recovery occurs, however, this is not
explicitly stated.


## Known Bugs

### s2.3.1

The Following rule:

    ASGN_EXPR      <- POSTFIX_EXPR "=" ASGN_EXPR | COND_EXPR

Was changed to:

    ASGN_EXPR      <- COND_EXPR "=" ASGN_EXPR | COND_EXPR
    
Currently, the code does not check and verify the the `COND_EXPR`
before the `=` is a `POSTFIX_EXPR`. This could lead to invalid `ASGN_EXPR`
such as `a ? b : c = 6;`.


