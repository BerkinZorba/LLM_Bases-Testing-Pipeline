## 📄 PROGRESS.md

### 📌 Project: LLM-Based Code and Test Generation

Course: BLG 475E - Software Quality and Testing

## 1. Project Initialization

- Created GitHub repository
- Defined project scope based on assignment requirements:
  - Code generation using LLMs
  - Unit and integration test generation
  - Coverage and quality analysis

- Reviewed project document and extracted key requirements

## 2. LLM Selection

### Goal

Select two publicly available LLMs suitable for:

- Java code generation
- Unit test generation (JUnit)

### Approach

- Decided to select:
  - One **general-purpose high-performance model**
  - One **code-specialized model**

### Rationale

- Enables meaningful comparison in:
  - Code correctness
  - Test quality
  - Handling of edge cases
  - Behavior in iterative workflows

### Candidate Models

- 
- 

## 3. Approach Design

### Two Approaches Considered

#### Semi-Agentic Approach

- Manual step-by-step process:
  - Generate code
  - Analyze output
  - Create next prompt manually

- Provides:
  - Higher control
  - Better interpretability

#### Agentic Approach

- Automated loop:
  - Code → Test → Feedback → Fix

- Provides:
  - Faster iteration
  - Reduced manual intervention

### Plan

- Use both approaches for comparison
- Evaluate strengths and limitations of each

## 4. Dataset Familiarization

- Reviewed HumanEval Java dataset
- Understood structure:
  - Problem descriptions
  - Base test cases

### Observations

- Problems vary in:
  - Complexity
  - Required logic depth
  - Edge-case sensitivity

## 5. Prompt Selection Strategy (Planned)

### Goal

Select 30 prompts with balanced difficulty

### Planned Distribution

- 10 Easy
- 10 Moderate
- 10 Hard

### Selection Criteria

- Diversity in problem types:
  - String manipulation
  - Arrays / Lists
  - Mathematical logic
  - Conditional logic

- Inclusion of edge-case-heavy problems
- Avoid redundancy in similar problem types

### Difficulty Classification Basis

- Number of logical steps
- Algorithmic complexity
- Presence of edge cases

## 6. Development Workflow (Planned)

- Each step will be committed separately with descriptive messages
- LLM interactions will be logged:
  - Prompt
  - Response
  - Modifications and reasoning

- Testing and improvements will follow iterative cycles

## 7. Next Steps

- Finalize LLM selection
- Select and categorize 30 prompts
- Begin code generation phase
- Establish prompt templates for consistency

## Notes

- All decisions are made to align with evaluation criteria:
  - Coverage
  - Correctness
  - Test effectiveness

- Progress will be updated after each major step and commit
