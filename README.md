# Assignment 3 — Bridge Pattern

## Student Information

- Name: Arnur Kudaibergen
- Group: SE-2529
- Topic: A — Drawing
- GitHub Repository: https://github.com/Arnur77/assignment3
- Base Commit: `4c0c592`

## 1. Topic

This assignment demonstrates the Bridge Design Pattern using a drawing application.

The abstraction hierarchy contains shapes:

- `Shape`
- `Circle`
- `Square`

The implementation hierarchy contains renderers:

- `Renderer`
- `VectorRenderer`
- `RasterRenderer`
- `AsciiRenderer`

Bridge connects these two independent hierarchies through the `Renderer` interface.

## 2. Role Map

| Bridge Role | Class | Source |
|---|---|---|
| Abstraction | `Shape` | `src/Shape.java` |
| Refined Abstraction A1 | `Circle` | `src/Circle.java` |
| Refined Abstraction A2 | `Square` | `src/Square.java` |
| Implementor | `Renderer` | `src/Renderer.java` |
| Concrete Implementor I1 | `VectorRenderer` | `src/VectorRenderer.java` |
| Concrete Implementor I2 | `RasterRenderer` | `src/RasterRenderer.java` |
| Concrete Implementor I3 | `AsciiRenderer` | `src/AsciiRenderer.java` |
| Client | `Main` | `src/Main.java` |

## 3. Bridge Structure

The `Shape` class stores an interface-typed reference:

```java
protected Renderer renderer;
```

This reference is the bridge between the abstraction hierarchy and the implementation hierarchy.

The renderer is supplied through the `Shape` constructor:

```java
public Shape(String id, Renderer renderer) {
    this.id = id;
    this.renderer = renderer;
}
```

The implementation can also be changed at runtime:

```java
public void setImplementation(Renderer renderer) {
    this.renderer = renderer;
}
```

The concrete shape classes delegate their work through the `Renderer` interface.

For example, `Circle` uses:

```java
return renderer.renderCircle(radius);
```

and `Square` uses:

```java
return renderer.renderSquare(side);
```

The abstraction classes do not create concrete renderers and do not check their concrete types.

## 4. Two Independent Dimensions

There are two independent dimensions in this implementation.

### Abstraction dimension

```text
Shape
├── Circle
└── Square
```

### Implementation dimension

```text
Renderer
├── VectorRenderer
├── RasterRenderer
└── AsciiRenderer
```

Because the two dimensions are separated, a shape can work with different renderers without creating a separate class for every combination.

For example:

```text
Circle + VectorRenderer
Circle + RasterRenderer
Circle + AsciiRenderer

Square + VectorRenderer
Square + RasterRenderer
Square + AsciiRenderer
```

## 5. Domain Data

The assignment uses fixed sample values:

- `Circle` uses radius `2`.
- `Square` uses side `3`.

The renderer output includes the shape and its dimension.

Examples:

```text
VECTOR circle radius=2
RASTER circle radius=2
ASCII circle radius=2

VECTOR square side=3
RASTER square side=3
ASCII square side=3
```

## 6. Runtime Implementation Switching

T5 demonstrates runtime switching.

The same `Circle` object is first used with `VectorRenderer`:

```text
VECTOR circle radius=2
```

Then the implementation is replaced:

```java
circle.setImplementation(new RasterRenderer());
```

The same object is executed again and produces:

```text
RASTER circle radius=2
```

T5 also checks reference equality using `==`.

The result is:

```text
sameObject=true
stateUnchanged=true
```

Therefore, the `Circle` object and its domain data remain unchanged while its implementation changes.

## 7. Adding the Independent I3 Implementation

The initial working version contained:

- `VectorRenderer`
- `RasterRenderer`

The base commit is:

```text
4c0c592
```

After the base commit, `AsciiRenderer` was added as I3.

The existing abstraction classes, `Renderer` interface, `VectorRenderer`, and `RasterRenderer` were not changed.

The extension is recorded in:

```text
extension.diff
```

The extension demonstrates that a new implementation can be added without changing the existing abstraction hierarchy.

## 8. Build and Run

The project can be compiled without an IDE using JDK 17:

```bash
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
```

Run the demonstration:

```bash
java -cp out Main --demo
```

## 9. Required Demonstration Checks

### T1 — Circle + VectorRenderer

Expected:

```text
VECTOR circle radius=2
```

### T2 — Circle + RasterRenderer

Expected:

```text
RASTER circle radius=2
```

### T3 — Square + VectorRenderer

Expected:

```text
VECTOR square side=3
```

### T4 — Square + RasterRenderer

Expected:

```text
RASTER square side=3
```

### T5 — Runtime Switch

Expected:

```text
sameObject=true
stateUnchanged=true
before=VECTOR circle radius=2
after=RASTER circle radius=2
```

### T6 — Circle + AsciiRenderer

Expected:

```text
ASCII circle radius=2
```

### T7 — Square + AsciiRenderer

Expected:

```text
ASCII square side=3
```

Final result:

```text
SUMMARY: 7/7 PASS
```

## 10. Files

```text
src/
├── Main.java
├── Shape.java
├── Circle.java
├── Square.java
├── Renderer.java
├── VectorRenderer.java
├── RasterRenderer.java
└── AsciiRenderer.java

sources.txt
README.md
demo-output.txt
extension.diff
```
