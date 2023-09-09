# MasterMind

<p align="center">
    <img src="https://github.com/Dizzydoze/MasterMind/assets/106438058/f8ee06c8-7ee5-4161-a3c4-a6a37e4be6e6">
</p>

- **Description**: A game of guessing multiple hidden colors. Players get multiple chances to guess 4 colors at a time for reviewing the secret hidden colors. There are two types of matches. **Exact**: Correct color with correct position. **Partial**: Correct color with wrong position. Mind that duplication exists. System will provide counts of exact matches and counts of partial matches as feedback.
- **Development process**:
    - **Process**: Two main difficulties. First, data members are being updated with each loop, I need to reset the data member for new exact checking. Then, duplication partial matches can be solved by replacing the character with special char every time there’s a partial match.
    - **Bugs**: StringBuilder, Char and String conversion is not as convenient as I thought.
    - **Concepts**: using String slices with StringBuilder makes things easier.
    - **algorithm**: Nothing special for now.
- **Test Cases**:
    - Input validation check.
    - Try to check partial match with duplications such as guessing YYRP for secret RPYY.
