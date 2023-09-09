# MasterMind

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f5280009-9d15-4886-819d-42a6ba324961/0424aa3e-ebf9-4ccc-b991-430c637f1cbd/Untitled.jpeg)

- **Description**: A game of guessing multiple hidden colors. Players get multiple chances to guess 4 colors at a time for reviewing the secret hidden colors. There are two types of matches. **Exact**: Correct color with correct position. **Partial**: Correct color with wrong position. Mind that duplication exists. System will provide counts of exact matches and counts of partial matches as feedback.
- **Development process**:
    - **Process**: Two main difficulties. First, data members are being updated with each loop, I need to reset the data member for new exact checking. Then, duplication partial matches can be solved by replacing the character with special char every time there’s a partial match.
    - **Bugs**: StringBuilder, Char and String conversion is not as convenient as I thought.
    - **Concepts**: using String slices with StringBuilder makes things easier.
    - **algorithm**: Nothing special for now.
- **Test Cases**:
    - Input validation check.
    - Try to check partial match with duplications such as guessing YYRP for secret RPYY.