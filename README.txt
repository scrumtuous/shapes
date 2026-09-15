Mermaid UML and Sequence Diagrams

Implementation 1
----------------
Based on the uploaded Java code:
- Surface is an interface.
- Shape is an abstract class implementing Surface.
- Rectangle, Point, Line, and Triangle extend Shape.
- Square extends Rectangle.
- Circle is outside the Shape/Surface hierarchy.

Implementation 2
----------------
Reworked interface-only design:
- Shape has been removed entirely.
- Every concrete class implements Surface directly.
- Each class declares its own fields and calcArea() implementation.
- Circle now implements Surface too, so it can participate in compareShapes().
- Line contains Point references.
- This is a conceptual redesign of the original source, not a literal diagram of the uploaded Java files.

All .mmd files use Mermaid syntax compatible with GitHub Markdown.
