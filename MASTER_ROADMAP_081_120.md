# Autonomous Agentic System — Master Roadmap 081–120

## Mission
Continue building the domain-agnostic autonomous system: user idea → project state → planning → task graph → capable-agent routing → execution → verification → recovery/replanning → goal completion or continuous operation. The book/writing workload remains only one domain.

## Roadmap rules
- This file is the master context for Tasks 081–120.
- Tasks are implementation milestones, not user-facing project work.
- GitHub is the persistent source of truth.
- Prefer generic reusable infrastructure over domain-specific logic.
- Future chunks continue as 121–160, 161–200, etc.

## Tasks

### Phase 9 — Execution records, observability, and deterministic state

**TASK-081 — Finalize execution record contract**  
Canonical job/execution fields, lifecycle, timestamps, parent task, retries, agent, outputs, and errors.

**TASK-082 — Implement execution persistence**  
Persist execution records so runs can be reconstructed after process restart.

**TASK-083 — Add correlation IDs**  
Trace one user request across project, task, execution, Git commit, agent session, and notification.

**TASK-084 — Build structured logging**  
Create machine-readable logs with severity, component, correlation ID, and context.

**TASK-085 — Build runtime event journal**  
Persist major orchestration events for audit and replay.

**TASK-086 — Build state reconciliation**  
Detect and reconcile inconsistencies among project, task, execution, and repository state.

**TASK-087 — Build idempotency keys**  
Prevent duplicate execution when the same trigger is delivered more than once.

**TASK-088 — Build stale-state detector**  
Detect tasks/jobs that appear active but have exceeded expected heartbeat/timeout windows.

**TASK-089 — Build restart recovery**  
Recover queued, running, and interrupted executions after orchestrator restart.

**TASK-090 — Add deterministic time/state handling**  
Centralize timestamp/state transitions to make tests and recovery reproducible.

### Phase 10 — Real async job runtime

**TASK-091 — Implement persistent job queue**  
Create a queue abstraction that survives process restarts and supports task priority.

**TASK-092 — Implement background executor**  
Separate HTTP/API responsiveness from long-running agent execution.

**TASK-093 — Implement worker leases**  
Use leases/heartbeats so abandoned jobs can be reclaimed safely.

**TASK-094 — Implement execution cancellation**  
Allow active jobs to be cancelled and state to be persisted consistently.

**TASK-095 — Implement execution timeout manager**  
Enforce task/job time limits independently of browser-generation waits.

**TASK-096 — Implement bounded concurrency**  
Respect configured project/system parallelism limits.

**TASK-097 — Implement dependency-aware scheduling**  
Schedule only tasks whose prerequisites are satisfied and resources are available.

**TASK-098 — Implement queue recovery tests**  
Verify restart, crash, duplicate-trigger, timeout, and cancellation behavior.

**TASK-099 — Implement asynchronous runtime status API**  
Expose run, job, queue, and current-worker state without blocking on agent execution.

**TASK-100 — Replace prototype synchronous `/tick` path**  
Make the generic runtime use the async job architecture as the canonical execution path.

### Phase 11 — Project intake and PM control plane

**TASK-101 — Build raw-idea intake endpoint**  
Accept a free-form user idea plus optional constraints and preferences.

**TASK-102 — Build intake clarification policy**  
Ask only high-value questions when the idea is materially underspecified; otherwise proceed with explicit assumptions.

**TASK-103 — Build project manifest compiler**  
Turn normalized intake into the canonical project manifest.

**TASK-104 — Build project validation before activation**  
Reject malformed objectives, impossible configurations, and missing required fields.

**TASK-105 — Build PM context package generator**  
Construct compact canonical context from project state, prior tasks, results, artifacts, and constraints.

**TASK-106 — Build PM planning interface**  
Provide a stable machine-facing contract for creating and revising plans.

**TASK-107 — Build task-plan validation**  
Check generated plans for coverage, duplicates, impossible dependencies, missing outputs, and missing capabilities.

**TASK-108 — Build plan versioning**  
Persist plan revisions and preserve why/when a plan changed.

**TASK-109 — Build automatic replanning triggers**  
Trigger PM review after completion, failure, blocked work, external change, or goal modification.

**TASK-110 — Build project completion evaluator**  
Determine whether success criteria are satisfied rather than assuming task completion means project completion.

### Phase 12 — Capability routing and agent management

**TASK-111 — Expand capability taxonomy**  
Define reusable capability names, specialization, compatibility, and prerequisites.

**TASK-112 — Implement scored agent selection**  
Score agents using capability match, health, workload, specialization, and policy.

**TASK-113 — Implement routing fallback policy**  
Select alternate agents when the preferred agent is unavailable or fails repeatedly.

**TASK-114 — Implement agent health checks**  
Track browser endpoint health, session validity, login state, and recent execution status.

**TASK-115 — Implement agent availability state**  
Represent AVAILABLE, BUSY, OFFLINE, ERROR, and maintenance conditions.

**TASK-116 — Implement agent capacity tracking**  
Track concurrent jobs and configured execution limits per agent.

**TASK-117 — Implement Worker 3 onboarding contract**  
Add a configuration-only path for enabling a newly available worker without core-code changes.

**TASK-118 — Implement capability mismatch handling**  
Stop safely when no configured agent can satisfy required capabilities.

**TASK-119 — Build routing audit records**  
Persist why an agent was selected, rejected, or replaced.

**TASK-120 — Build routing test matrix**  
Test capability matches, ties, unavailable agents, overloaded agents, fallbacks, and no-capability cases.

## Completion target for this chunk
By Task 120, the runtime should have persistent execution/state history, non-blocking background jobs, restart-safe queueing, project-intake and PM control-plane foundations, and a robust capability-based agent management layer. The system should be able to accept a generic idea and move toward autonomous execution without relying on book-specific assumptions.