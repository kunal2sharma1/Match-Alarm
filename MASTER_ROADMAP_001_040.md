# Autonomous Agentic System — Master Roadmap 001–040

## Mission
Build a domain-agnostic autonomous system that accepts a user's idea, turns it into a persistent project, plans and decomposes work, routes tasks to capable agents, executes, verifies, recovers from failure, and continues until the goal is achieved or human judgment is required. Writing is only the first test domain.

## Roadmap rules
- This file is the master context for Tasks 001–040.
- Tasks are implementation milestones, not user-facing project work.
- GitHub is the persistent source of truth.
- The current book workflow is a regression/test workload, not the architecture.
- Future chunks continue as separate roadmap files: 041–080, 081–120, etc.

## Tasks

### Phase 1 — Baseline and architecture

**TASK-001 — Baseline repository audit**  
Inventory the current prototype, working scripts, configurations, browser-agent assumptions, dependencies, and known defects. Output: baseline findings.

**TASK-002 — Define V1 system boundary**  
Document what the autonomous core owns versus PM agents, worker agents, GitHub, n8n, Playwright, Ollama, and Telegram.

**TASK-003 — Define authoritative state model**  
Define project, task, agent, execution, validation, and human-intervention states and ownership of each transition.

**TASK-004 — Define event model**  
Define the events that cause the system to observe, plan, execute, validate, retry, escalate, and replan.

**TASK-005 — Define failure/recovery policy**  
Define retries, timeouts, escalation, quarantine, cancellation, and human-intervention rules.

### Phase 2 — Generic data/control plane

**TASK-006 — Finalize project manifest schema**  
Create the canonical machine-readable project definition: objective, success criteria, constraints, mode, requirements, inputs, outputs, and runtime policy.

**TASK-007 — Finalize task schema**  
Define canonical task fields, dependency representation, capability requirements, outputs, acceptance criteria, timestamps, attempts, and lineage.

**TASK-008 — Finalize execution record schema**  
Define execution/job records including execution ID, task ID, agent, start/end state, errors, retries, and artifacts.

**TASK-009 — Finalize human-decision schema**  
Define decision requests, options, responses, expiry, audit trail, and resume behavior.

**TASK-010 — Create schema validation layer**  
Implement reusable validation for project, task, execution, and decision records.

### Phase 3 — Generic project intake

**TASK-011 — Build project intake API**  
Create the interface that accepts a raw user idea plus optional constraints/preferences.

**TASK-012 — Build idea normalization**  
Convert unstructured input into a structured objective without assuming a particular domain.

**TASK-013 — Build project-type detection**  
Determine writing, research, software, business, or custom project type from the objective.

**TASK-014 — Build capability requirement extraction**  
Translate the project into required capabilities rather than fixed agent names.

**TASK-015 — Build success-definition extraction**  
Generate measurable success criteria, required outputs, constraints, and stop conditions.

### Phase 4 — Project lifecycle

**TASK-016 — Build project creation service**  
Persist a new project manifest and initialize project state.

**TASK-017 — Build project resume/pause lifecycle**  
Allow projects to pause, resume, and recover without losing state.

**TASK-018 — Build goal vs continuous mode**  
Implement explicit goal mode (stop on completion) and continuous mode (observe/replan indefinitely).

**TASK-019 — Build project context builder**  
Create the canonical context package supplied to PM/planning agents from project state, tasks, results, and artifacts.

**TASK-020 — Build project state persistence**  
Persist and reconstruct project state entirely from repository data without relying on chat memory.

### Phase 5 — Task graph and routing

**TASK-021 — Build task graph engine**  
Represent tasks and dependencies as an executable graph.

**TASK-022 — Build dependency resolver**  
Determine which READY tasks are actually executable based on completed dependencies.

**TASK-023 — Build task priority engine**  
Support priority, urgency, deadlines, and dependency-aware ordering.

**TASK-024 — Build capability-based agent router**  
Select agents by required capabilities, availability, workload, and fallback policy.

**TASK-025 — Build agent registry/health model**  
Track enabled agents, capabilities, browser endpoint, availability, health, and operational state.

### Phase 6 — Agent execution runtime

**TASK-026 — Separate browser runtime from orchestration**  
Refactor Playwright/browser code into a reusable agent-runtime adapter independent of project logic.

**TASK-027 — Build agent session manager**  
Handle browser connection, session validation, login detection, reconnect, and isolated profiles.

**TASK-028 — Build reliable message transport**  
Implement robust prompt injection, ProseMirror handling, generation detection, response extraction, and large-response handling.

**TASK-029 — Build execution job manager**  
Represent worker executions as jobs with lifecycle, timeout, heartbeat, and cancellation support.

**TASK-030 — Build worker execution adapter**  
Give the orchestrator one generic interface for activating any configured agent.

### Phase 7 — Orchestration and validation

**TASK-031 — Build orchestration state machine**  
Implement observe → decide → claim → execute → validate → recover/replan transitions.

**TASK-032 — Build task claiming/locking**  
Implement safe task ownership, idempotency, leases, and concurrency protection.

**TASK-033 — Build result/artifact collector**  
Collect worker results and repository artifacts into the canonical execution record.

**TASK-034 — Build validation pipeline**  
Validate output existence, schemas, acceptance criteria, structural consistency, and project-level constraints.

**TASK-035 — Build failure/retry engine**  
Automatically retry suitable failures, change execution route when appropriate, and escalate after retry limits.

### Phase 8 — Autonomous runtime infrastructure

**TASK-036 — Convert synchronous orchestration to background jobs**  
Make `/tick` enqueue work and return immediately; expose job/status APIs.

**TASK-037 — Build runtime API**  
Implement `/health`, `/status`, `/projects`, `/tasks`, `/executions`, and `/tick` interfaces.

**TASK-038 — Build scheduler/event trigger layer**  
Connect the runtime to scheduled and event-driven triggers without placing decision logic inside the scheduler.

**TASK-039 — Build PM continuation/replanning service**  
Automatically activate PM when planning is needed, review completed work, and generate exactly the next executable plan/task set according to policy.

**TASK-040 — End-to-end autonomous loop test**  
Run a generic test project from raw idea → project manifest → PM plan → task routing → worker execution → validation → completion/replanning with no manual task-by-task intervention.

## Completion target for this chunk
At Task 040, the system should have a generic project intake layer, persistent project/task state, capability-based routing, reusable browser-agent execution, validation/recovery, background orchestration, scheduler integration, and a complete autonomous loop test. The writing/book workload should be only one domain adapter on top of this core.