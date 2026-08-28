# Agentic System Audit — 2026-08-29

## Scope
Audit the current `kunal2sharma1/agentic-system` implementation against the consolidated autonomous-platform architecture recorded in Match-Alarm.

## Executive verdict
The repository contains a working browser-agent prototype plus the first generic-core scaffolding. It is **not yet a general autonomous operating system**. The prototype should be preserved as a regression/reference layer while the generic runtime is rebuilt around explicit contracts.

## Confirmed strengths
- Existing Chrome-over-CDP execution is correct and should remain the browser transport: `controller.py` uses Playwright `chromium.connect_over_cdp()` against configured Chrome ports.
- PM/worker browser automation has already been demonstrated in real execution.
- GitHub-backed task/result persistence exists.
- Generic project, task, router, state, loader, and validation modules exist.
- Project modes already include `goal` and `continuous`.
- Agent registry is capability-oriented and currently defines PM, Worker 1, Worker 2, with Worker 3 disabled.

## Critical findings

### 1. Two configuration systems
`config/agents.json` and `config/agent_registry.json` coexist. The old controller reads `agents.json`; the generic router reads `agent_registry.json`. This creates split-brain configuration.

**Action:** establish one canonical registry and make legacy controller consume it through an adapter.

### 2. Runtime state is not transactional
Current `core/state_manager.py` is an in-memory dataclass. The orchestrator also has `.orchestrator-runtime.json`. Neither is a durable transactional operational state store.

**Action:** implement a canonical operational state layer before serious queue/concurrency work. GitHub remains artifact/control-plane storage, not the transactional runtime database.

### 3. Task engine is in-memory only
`core/task_engine.py` stores tasks in a Python dictionary and only checks READY/dependency satisfaction. It lacks persistent lifecycle transitions, leases, attempts, priorities, cancellation, lineage, and recovery semantics.

**Action:** make task lifecycle a persisted service backed by the canonical state store.

### 4. Router is too naive
`choose_agent()` performs simple capability intersection and does not account for agent health, workload, model choice, latency, privacy, cost, or fallback.

**Action:** create one routing contract covering agent + model + tool selection.

### 5. Project model is incomplete
`project_engine.py` validates a few fields but does not own project lifecycle, policy, deadlines, budgets, autonomy controls, identity, or provenance.

**Action:** expand the project contract, then keep lifecycle semantics canonical.

### 6. Validation is far too weak
`core/validator.py` currently verifies only output existence and non-empty files. This is insufficient for acceptance criteria or semantic correctness.

**Action:** implement generic structural validation, acceptance-criteria validation, evidence/provenance, and later domain validators.

### 7. Orchestrator is still prototype-level
`orchestrator.py` is tightly coupled to `controller.run_agent`, filesystem task parsing, Git CLI operations, and the single-agent-per-cycle assumption. It contains controller-owned task claiming, which conflicts with the existing protocol's worker-owned lifecycle wording.

**Action:** resolve state ownership once, then isolate orchestrator from browser details and Git implementation details behind interfaces.

### 8. Synchronous execution remains embedded
`execute_worker()` opens a synchronous Playwright context and waits for a complete ChatGPT response. This is not a scalable background execution runtime.

**Action:** build a job/worker execution abstraction first; browser execution becomes one execution adapter.

### 9. PM logic is still book/prototype oriented
`pm_cycle.py` still contains explicit rules such as creating exactly one task and references the old project flow. It is not the generic planner contract required for arbitrary projects.

**Action:** keep it as legacy regression code; create a generic planner interface.

### 10. Browser abstraction is incomplete
The browser controller is proven but still contains UI selectors and ChatGPT-specific behavior directly in controller code.

**Action:** extract a browser-agent adapter contract while retaining CDP to existing Chrome.

### 11. Security boundaries are not implemented
No canonical prompt trust model, tool authorization model, secret-reference architecture, sandbox enforcement, identity layer, privacy policy, or irreversible-action policy is present in the inspected core.

**Action:** build security/governance before broad external tool actions.

### 12. No tool abstraction
Domains describe capabilities, but there is no canonical governed tool registry/contract for browser, file, API, email, calendar, shell, etc.

**Action:** define one tool contract and route tools by capability/policy.

### 13. No model/provider abstraction
Current execution assumes ChatGPT browser accounts. The architecture requires provider-neutral model selection/fallback while treating the user's existing accounts as finite resources.

**Action:** add model/provider interfaces without requiring paid services for V1.

### 14. No proper evaluation framework
There is no benchmark/golden-project framework proving that the system plans and executes correctly across domains.

**Action:** create deterministic evaluation projects and regression thresholds before calling the system autonomous.

## Zero-cost constraint
V1 should remain implementable using the user's existing PC, local/open-source software, GitHub, self-hosted n8n, existing model accounts, and local models. No paid API or paid cloud service should be a prerequisite.

## Required architectural split

```text
Legacy prototype
  ├── controller.py
  ├── pm_cycle.py
  └── task_runner.py
        ↓
  Regression/reference only

Generic platform
  ├── project contract
  ├── planning contract
  ├── task/state contract
  ├── execution runtime
  ├── agent/model routing
  ├── tool runtime
  ├── validation
  ├── recovery
  ├── security/policy
  ├── human control
  ├── continuous loop
  └── domain adapters
```

## User action required now
**No destructive action is required.** The current local changes were checkpointed to GitHub before this audit. Do not reset or delete prototype files yet.

The next implementation phase should be a clean generic-core build, beginning with the canonical architecture/contracts and state model. Legacy browser/task scripts should continue to serve as regression fixtures until their responsibilities are migrated.

## Recommended immediate implementation order
1. Freeze architecture contracts.
2. Define canonical state and lifecycle semantics.
3. Define one agent registry/configuration source.
4. Define execution/job contract.
5. Define planner/task contracts.
6. Build minimal transactional local state store using standard-library SQLite so V1 stays zero-cost.
7. Build execution queue/lease layer.
8. Adapt the proven Chrome-CDP controller behind an execution adapter.
9. Replace book-specific PM cycle with generic planning interface.
10. Build end-to-end generic synthetic project test before adding more domains.

## Audit conclusion
The project is **healthy enough to continue**, but it is at the transition from prototype to architecture. The biggest risk is not missing code; it is allowing the old browser-centric design to become the permanent architecture.
