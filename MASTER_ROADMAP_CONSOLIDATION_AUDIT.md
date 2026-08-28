# Master Roadmap Consolidation Audit — 001–680

## Purpose
Consolidate the roadmap before implementation. The objective is to remove duplicate work, identify prerequisites that must move earlier, distinguish implementation from certification, and establish a single canonical architecture. This audit does not add another arbitrary task block.

## Decision
STOP expanding the roadmap by default. Do not create Tasks 681+ until this consolidation is completed and the remaining implementation scope is justified.

The existing audit identified repeated work in asynchronous execution, PM planning/replanning, validation, agent health/routing, project lifecycle, security, continuous mode, tools/integrations, and artifact/GitHub storage. It also identified missing or insufficiently explicit work around model/provider abstraction, prompt/context security, execution sandboxing, operational state storage, configuration/secrets, identity/authorization, provider-aware accounting, evaluation, privacy/data lifecycle, time semantics, distributed boundaries, explainability, event trust, irreversible-action guardrails, and model migration testing.

## Canonical architecture

1. **Project layer** — project manifest, objective, constraints, success criteria, lifecycle.
2. **Planning layer** — PM/planner contract, task graph, dependencies, prioritization, replanning.
3. **Execution layer** — scheduler, queue, leases, worker execution, tool execution, concurrency.
4. **Agent/model layer** — agent registry, capability profiles, model/provider abstraction, routing, fallback, health.
5. **Tool layer** — governed tool contract, permissions, risk, dry-run, idempotency, rate limits, external-event trust.
6. **State layer** — transactional operational state store; GitHub is an artifact/control-plane adapter, not the runtime database.
7. **Validation layer** — generic validators, domain validators, evidence, acceptance gates, regression benchmarks.
8. **Recovery layer** — failure taxonomy, retries, alternate routing, quarantine, escalation, restart/reconciliation.
9. **Security/governance layer** — identity, authorization, secrets, prompt-injection defense, sandboxing, privacy, prohibited actions.
10. **Human-control layer** — approvals, decisions, pause/resume/cancel, explanations, evidence, audit.
11. **Continuous-operation layer** — event loop, schedules, observation, change detection, stop policies, resource limits.
12. **Domain-adapter layer** — writing, research, software, business/operations, and future custom domains.
13. **Interface layer** — CLI/operator workflow, API, UI, notifications.
14. **Operations layer** — observability, backup/restore, deployment, upgrades, disaster recovery, release validation.

## Consolidation rules

- A behavior gets one canonical implementation task/workstream.
- Later occurrences become integration, scale, regression, or certification tasks; they must not redefine the same contract.
- GitHub remains the durable project/artifact source of truth where appropriate, but runtime queues, locks, leases, and transactional state must not depend on Git commits.
- Security controls are cross-cutting gates applied to every external-action capability, not isolated features that are forgotten later.
- Domain-specific behavior must remain outside the generic orchestrator.
- Continuous autonomy must use the same canonical execution/recovery/state contracts as ordinary projects.
- Certification tasks prove integration; they do not introduce new architecture.
- Optional multi-user, distributed, and advanced compliance features must be explicitly separated from the single-user zero-cost V1 target.

## Existing roadmap areas to merge conceptually

### Execution runtime
Merge the repeated async/execution work into one canonical runtime stream. Preserve later concurrency and scale tests as extensions only.

### Planning/replanning
Define one planner contract. Earlier planner implementation is foundational; later planning items become adaptive-planning, integration, and benchmark work.

### Validation
Keep one validation framework, then separate domain validators and release certification. Do not implement multiple generic validators.

### Agent routing/health
Use one registry + capability + health contract. Provider/model routing becomes a sub-layer of this architecture rather than another registry.

### Lifecycle
Create one canonical project/task lifecycle state machine. Pause, resume, cancel, archive, migration, and restart must reuse it.

### Security
Use one threat model and policy architecture. Prompt injection, tool authorization, secrets, sandboxing, privacy, and irreversible actions are enforcement modules under the same governance boundary.

### Continuous operation
Use one control-loop supervisor and event model. Scheduling, change detection, and continuous projects are inputs to the same loop.

### Tools/integrations
Separate the generic tool runtime contract from provider-specific adapters. Do not create multiple competing tool registries.

### Storage
Separate operational state from artifacts/audit. GitHub is an adapter for repository-backed artifacts and auditable control files, not the transactional execution database.

## Required implementation order

### Gate A — Architecture
Freeze contracts for project, task, execution, agent, model/provider, tool, state, validation, recovery, policy, and human decision objects.

### Gate B — State + execution
Implement transactional runtime state, queues/leases, lifecycle transitions, execution, and recovery before advanced autonomy.

### Gate C — Model/agent/tool governance
Implement model/provider abstraction, capability routing, tool authorization, external-event verification, and resource accounting.

### Gate D — Security
Implement identity, authorization, secret references, prompt/context boundaries, sandboxing, privacy/data lifecycle, and irreversible-action policy before broad external actions.

### Gate E — Autonomous loop
Implement canonical event model, scheduler, control loop, completion/no-work/continuous decisions, and runaway protections.

### Gate F — Domains
Certify writing, research, software, business/operations, then custom-domain extension.

### Gate G — Human interface
Expose high-level project creation, status, decisions, approvals, explanations, pause/resume, and one-command operation.

### Gate H — Release proof
Run benchmarks, reliability, security, restart/disaster, usability, multi-domain, and low-risk real-world pilot tests.

## Zero-cost constraint

The roadmap must not require paid APIs or paid infrastructure for V1. Preferred resources are local/open-source components, the user's existing machines, GitHub free capabilities, self-hosted automation, and the user's existing model accounts. Paid providers may exist behind adapters but must be optional rather than architectural prerequisites.

## Single-user V1 boundary

Mandatory V1:
- one operator
- local/self-hosted runtime
- multiple browser/model accounts as finite resources
- free/open-source tooling where practical
- generic project/task execution
- research, writing, software, and business/operations adapters
- human approval for high-risk actions
- continuous operation with bounded policies
- durable state and artifacts
- recovery and restart
- measurable evaluation

Optional post-V1:
- multi-user tenancy
- distributed orchestrators
- leader election across nodes
- advanced enterprise compliance
- paid provider optimization
- large-scale cloud deployment

## Roadmap status

Tasks 001–680 should be treated as a **candidate roadmap**, not 680 independent commitments. The next engineering step is to map every candidate task to the canonical architecture, mark it as KEEP / MERGE / MOVE / OPTIONAL / CERTIFICATION, and then produce a deduplicated implementation sequence.

Do not increase the task count until that mapping is complete.

## Finish condition for this audit

The roadmap is considered consolidated only when:

- every candidate task has one owner/workstream;
- no two tasks implement the same contract;
- foundational dependencies precede consumers;
- security gates precede external authority;
- operational state is separated from GitHub artifacts;
- model/provider abstraction precedes routing optimization;
- domain adapters depend on generic interfaces;
- certification tasks are clearly separated from implementation;
- optional V1.1 work is separated from mandatory V1;
- the final task count is justified by capability coverage rather than an arbitrary target.

## Current recommendation

**Do not proceed to Tasks 681–720.** Perform the consolidation mapping first. After that, freeze the final V1 task list and begin implementation from the earliest unmet architectural gate.
