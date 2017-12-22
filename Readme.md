# AntiSpamConfiguration

- Class: LEI-PL, LIGE-PL
- Group: 107
- Members:
  - 61161, Michael Carvalho, Developer
  - 62189, Miguel Amaro, Developer
  - 65175, Mauro Pinto, Scrum Master
  - 73640, Maria João Canedo, Quality Manager
- Variant: AntiSpamConfigurationForLeisureMailbox
- [Demo](https://www.youtube.com/watch?v=1bhlzjUAcjE)

## Additional features

- All variants were implemented as shown in the video and existing folders
- GUI was simplified to allow random and NSGA generation along with manual changes in a single table
- The application memorizes the last location used to choose a file, easing the selection of multiple files
- Exceptions are caught and shown with (somewhat) user friendly messages
- Input validation and specification rules upheld (weights only assume values between -5 and 5, etc...)

## Technical considerations

- We used a HashSet to store the Rules associated with a Message (Ham or Spam) because
 it's the structure that allows the fastest search
 (O(1) time complexity, against O(n) from collections such as ArrayList).
 This is specially important considering the evaluations can be run thousands of times. 
- `Rscript` and `pdflatex` are assumed to be in the path of the operating system.
 It was tested in both Windows and MacOS
