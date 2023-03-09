# Big Nerd Ranch - Android Programming

## Course notes

### Chapter 3: The Activity Lifecycle
#### Challenge 1: Preventing Repeat Answers

Goal statement: Once a user provides an answer for a particular question, disable the buttons for that question to prevent multiple answers being entered.

No matter what, you have to keep track of the "answered" state, so all approaches will have that in common. This table considers different ways to achieve the objective "disable the button".

*Pre-work: in each T/F button onClickListener, add `if(!answered)` conditional for `checkAnswer()`*

Approaches:
| Strategy | Pros | Cons |
| - | - | - |
| Show toast, buttons unchanged | easy | non-persistent text |
| Add text view, remove buttons | more robust, allows user to navigate | idk how to do this, the end results page should be a new activity |

Other considerations:
* Do not add `if(!answered)` conditional and put `setOnClickListener` in there; not accessible to have button with no listener.