# Autonomous Agentic System — Master Roadmap 161–200

## Mission
Continue the domain-agnostic autonomous system: idea → project → plan → executable graph → capable agents → execution → verification → recovery → human control → continuous operation. Domain adapters remain separate from the core.

## Roadmap rules
- This file is the master context for Tasks 161–200.
- Tasks are implementation milestones, not project-specific work.
- GitHub remains the persistent source of truth.
- Prefer generic reusable infrastructure.
- Preserve compatibility with Tasks 001–160.
- Future chunks continue as 201–240, 241–280, etc.

## Tasks

### Phase 17 — Generic PM planning and replanning engine

**TASK-161 — Finalize PM system prompt contract**  
Define stable inputs, outputs, constraints, decision authority, and forbidden behaviors for PM operations.

**TASK-162 — Implement bounded PM context assembly**  
Build compact context from project state, plans, tasks, results, decisions, and relevant artifacts.

**TASK-163 — Implement plan creation adapter**  
Convert PM output into canonical machine-readable plan records.

**TASK-164 — Implement plan semantic validation**  
Check that a proposed plan actually addresses the project objective and success criteria.

**TASK-165 — Implement plan coverage analysis**  
Detect missing work required to reach declared outputs and success conditions.

**TASK-166 — Implement plan redundancy analysis**  
Detect duplicate or overlapping tasks before execution.

**TASK-167 — Implement plan dependency validation**  
Validate dependency ordering and reject impossible or cyclic graphs.

**TASK-168 — Implement plan resource validation**  
Check required capabilities, agent availability, runtime limits, and external prerequisites.

**TASK-169 — Implement incremental replanning engine**  
Revise only affected portions of an existing plan when new information changes the path.

**TASK-170 — Build PM planning regression suite**  
Test initial plans, incomplete requirements, failures, scope changes, external changes, and completion decisions.

### Phase 18 — Project knowledge and memory

**TASK-171 — Define project knowledge model**  
Separate durable facts, decisions, assumptions, evidence, artifacts, and transient execution state.

**TASK-172 — Implement canonical project facts store**  
Persist verified project facts that future agents can safely reuse.

**TASK-173 — Implement decision memory**  
Persist strategic decisions, rationale, alternatives considered, and affected scope.

**TASK-174 — Implement assumption tracking**  
Record assumptions with confidence, owner, evidence, and validation status.

**TASK-175 — Implement evidence/provenance records**  
Track where important facts or outputs originated and which artifacts support them.

**TASK-176 — Implement project context retrieval**  
Retrieve only relevant durable context for planning and execution instead of loading entire history.

**TASK-177 — Implement memory conflict detection**  
Detect contradictory facts, obsolete decisions, and incompatible assumptions.

**TASK-178 — Implement memory update policy**  
Define when an execution may create, revise, supersede, or invalidate durable knowledge.

**TASK-179 — Implement project memory compaction**  
Summarize long histories while preserving authoritative facts and decisions.

**TASK-180 — Build project-memory integration tests**  
Verify persistence, retrieval, contradiction handling, updates, compaction, and restart reconstruction.

### Phase 19 — Artifact and workspace management

**TASK-181 — Define artifact taxonomy**  
Classify source files, generated outputs, evidence, reports, builds, exports, and temporary artifacts.

**TASK-182 — Implement artifact metadata records**  
Track path, type, creator, task, execution, version, checksum, and lifecycle.

**TASK-183 — Implement artifact version tracking**  
Preserve lineage when outputs are revised or superseded.

**TASK-184 — Implement artifact ownership rules**  
Define which agent/task may create, modify, approve, or supersede each artifact class.

**TASK-185 — Implement workspace isolation**  
Provide safe per-execution working directories to prevent cross-task contamination.

**TASK-186 — Implement artifact promotion workflow**  
Move validated outputs from temporary workspace into canonical project state.

**TASK-187 — Implement artifact cleanup policy**  
Remove or archive temporary artifacts without deleting required audit evidence.

**TASK-188 — Implement artifact checksum/integrity checks**  
Detect unexpected modifications between creation, validation, and promotion.

**TASK-189 — Implement artifact storage abstraction**  
Support GitHub and future storage backends through a stable interface.

**TASK-190 — Build artifact-management integration tests**  
Test creation, versioning, isolation, promotion, cleanup, integrity, and recovery.

### Phase 20 — Security and authority boundaries

**TASK-191 — Define agent authority model**  
Specify what PM, workers, orchestrator, scheduler, and human operators may read or modify.

**TASK-192 — Implement operation authorization checks**  
Enforce permissions before state changes, artifact writes, execution, and external actions.

**TASK-193 — Implement secret/configuration isolation**  
Prevent credentials and sensitive runtime configuration from entering project artifacts or logs.

**TASK-194 — Implement agent account isolation**  
Keep browser profiles, authentication state, and agent-specific credentials separated.

**TASK-195 — Implement sensitive-data redaction**  
Redact secrets and sensitive values from logs, notifications, and persisted execution records.

**TASK-196 — Implement privileged-action approval policy**  
Require human approval for configurable high-impact or irreversible actions.

**TASK-197 — Implement security audit records**  
Record privileged operations, authorization decisions, failures, and overrides.

**TASK-198 — Implement secure external-action interface**  
Create a controlled abstraction for future email, browser, API, deployment, and other external actions.

**TASK-199 — Implement security threat test suite**  
Test unauthorized state changes, secret leakage, privilege escalation, unsafe actions, and boundary violations.

**TASK-200 — Security/authority integration review**  
Review the complete core architecture for permission gaps before expanding autonomous external actions.

## Completion target for this chunk
By Task 200, the system should have a reliable PM planning/replanning layer, durable project knowledge, controlled artifact lifecycle, and explicit security/authority boundaries. The core should increasingly behave like a general autonomous project operating system rather than a collection of browser scripts.