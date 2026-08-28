# Master Roadmap Final Consolidation — Candidate Tasks 001–680

## Purpose
This is the consolidation decision record for the current 001–680 candidate roadmap. It prevents roadmap inflation and establishes one canonical implementation path before further engineering.

## Hard decision
**Do not create Tasks 681+.** The current roadmap already contains repeated contracts. The objective is capability coverage, not a large task count.

## Canonical architecture
1. Project/intake
2. Planning/task graph
3. Transactional runtime state
4. Scheduler/queue/leases/execution
5. Agent + model/provider abstraction
6. Governed tools/integrations
7. Validation/evidence
8. Recovery/reconciliation
9. Security/identity/privacy/sandbox
10. Human control
11. Continuous control loop
12. Domain adapters
13. Interface/operator layer
14. Observability/operations/release
15. Knowledge/memory
16. Multi-project coordination

## Decision rules
- KEEP = unique implementation capability.
- MERGE = same contract/capability already implemented or defined elsewhere; retain one canonical implementation task.
- MOVE = capability is valid but belongs earlier/later in dependency order.
- CERTIFICATION = keep only as a test/acceptance milestone, never as a second implementation.
- OPTIONAL = outside zero-cost single-user V1; defer without blocking V1.
- REWRITE = task intent is retained but wording/ownership must change to fit the canonical architecture.

## Chunk-level consolidation map

| Candidate range | Decision | Canonical treatment |
|---|---|---|
| 001–040 | KEEP + MOVE | Foundational architecture, project intake, lifecycle, task graph, agent runtime, orchestration and first autonomous loop. Transactional runtime state must be separated from GitHub before treating GitHub as runtime storage. |
| 041–080 | KEEP + MERGE | Durable execution, leases, heartbeats, restart, browser hardening, GitHub adapter, PM planning. Merge duplicate execution/browser contracts with 001–040 rather than creating competing implementations. |
| 081–120 | KEEP/MERGE | Retain unique validation, recovery, human-intervention and runtime capabilities; merge anything that redefines the same state, execution or planning contract. |
| 121–160 | KEEP/MERGE | Retain unique scheduling, concurrency, validation and project-control capabilities; consolidate duplicate orchestration/control-loop work. |
| 161–200 | KEEP/MERGE | Retain unique persistence, observability, artifact and agent-management capabilities; one canonical implementation per subsystem. |
| 201–240 | KEEP/MERGE | Retain unique domain-adapter and project-continuity capabilities; generic functionality remains in core. |
| 241–280 | KEEP/MERGE | Retain unique recovery, validation, scheduling and external-action capabilities; eliminate competing versions of generic subsystems. |
| 281–320 | KEEP/MERGE | Retain unique tool/integration and execution capabilities; route through one governed tool layer. |
| 321–360 | KEEP/MERGE | Retain unique autonomy, planning and human-control capabilities; consolidate repeated autonomous-loop logic. |
| 361–400 | KEEP/MERGE | Retain unique reliability, evaluation and domain integration capabilities; certification is separated from implementation. |
| 401–440 | KEEP/MERGE | Retain unique operational, security and deployment capabilities; move security gates ahead of external authority. |
| 441–480 | KEEP/MERGE | Retain unique monitoring, continuous operation, policy and evaluation capabilities; one canonical event/control-loop architecture. |
| 481–520 | KEEP/MERGE | Retain unique productization, operations, extensibility and final integration capabilities; do not duplicate core contracts. |
| 521–560 | KEEP, but dependency-audit | This is the strongest V1 integration/release layer. Its event model, control loop, generic lifecycle, domain certification and release gates remain canonical, but must consume the earlier canonical state/execution/security contracts. |
| 561–600 | PARTIAL KEEP/MERGE | 561–570 model/provider abstraction is canonical. 571–580 cognition-security is canonical. 581–590 sandbox is canonical. 591–600 personal workflow is mostly new and retained. Any overlap with earlier work becomes integration rather than duplicate implementation. |
| 601–640 | HEAVY MERGE/REWRITE | 601–610 duplicate 561–570; 611–620 duplicate 571–580; 621–630 duplicate 581–590. Retain 631–640 as identity/privacy/data-governance work, but merge overlapping minimization/authorization/security controls into the single governance architecture. |
| 641–680 | KEEP WITH MERGES | Knowledge, adaptive planning, tool governance and multi-project coordination are valuable. Merge 648 with context-budget management where applicable; merge 649/675/676 with one knowledge/data/project isolation policy; merge 668 with canonical provenance. Keep 671–680 as the portfolio layer. |

## Detailed decisions for 561–680

### 561–570 — Model/provider
- 561 KEEP — canonical provider contract.
- 562 KEEP — provider registry, distinct from agent registry.
- 563 KEEP — model selection policy.
- 564 KEEP — fallback.
- 565 KEEP — model health.
- 566 KEEP — accounting.
- 567 KEEP — privacy routing.
- 568 KEEP — prompt/template versioning.
- 569 CERTIFICATION — simulation tests.
- 570 CERTIFICATION — multi-provider integration.

### 571–580 — Cognition security
- 571 KEEP — canonical trust boundary.
- 572 KEEP — provenance/trust labels.
- 573 KEEP — injection detection hooks.
- 574 KEEP — instruction isolation.
- 575 KEEP — tool-output sanitization.
- 576 KEEP — sensitive context minimization.
- 577 CERTIFICATION — adversarial tests.
- 578 KEEP — refusal/escalation policy.
- 579 KEEP — security audit trail.
- 580 CERTIFICATION — acceptance test.

### 581–590 — Sandbox
- 581 KEEP — canonical execution isolation policy.
- 582 KEEP — workspace sandbox.
- 583 KEEP — command policy.
- 584 KEEP — network policy.
- 585 KEEP — filesystem policy.
- 586 KEEP — resource limits.
- 587 KEEP — violation handling.
- 588 CERTIFICATION — escape tests.
- 589 CERTIFICATION — secure execution integration.
- 590 CERTIFICATION — security review.

### 591–600 — Personal workflows
- 591 KEEP — generic workflow abstraction.
- 592 KEEP — workflow templates.
- 593 KEEP — natural-language project modification.
- 594 KEEP — conversational control.
- 595 KEEP — user preference model.
- 596 MERGE — personal-data boundary must use the canonical data-governance model from 631+.
- 597 KEEP — bounded proactive opportunity detection.
- 598 KEEP — proactive-action approval policy.
- 599 CERTIFICATION — personal-assistant workflow test.
- 600 CERTIFICATION/REVIEW — expansion review; not a new architecture.

### 601–610 — Duplicate provider block
MERGE into 561–570. No second provider abstraction, registry, health system or multi-provider contract.

### 611–620 — Duplicate cognition-security block
MERGE into 571–580. Preserve any additional requirements as tests or extensions to the canonical security model.

### 621–630 — Duplicate sandbox block
MERGE into 581–590. Preserve implementation details that are stricter or more complete; discard competing contracts.

### 631–640 — Identity/privacy/data governance
- 631 KEEP — canonical data classification.
- 632 KEEP — canonical data authorization policy.
- 633 MERGE — data minimization belongs in one governance policy.
- 634 KEEP — retention/deletion lifecycle.
- 635 KEEP — privacy-aware audit records.
- 636 KEEP — consent/approval controls.
- 637 KEEP — human identity binding.
- 638 CERTIFICATION — privacy/authorization suite.
- 639 CERTIFICATION — governance recovery test.
- 640 CERTIFICATION/REVIEW — architecture acceptance gate.

### 641–650 — Knowledge/memory
- 641 KEEP — canonical knowledge model.
- 642 KEEP — knowledge-store abstraction.
- 643 KEEP — provenance.
- 644 KEEP — confidence.
- 645 KEEP — conflict model.
- 646 KEEP — supersession.
- 647 KEEP — relevance retrieval.
- 648 MERGE/REWRITE — use the model context-budget system plus canonical knowledge assembly.
- 649 MERGE/REWRITE — use canonical project/data isolation policy.
- 650 CERTIFICATION — knowledge integrity suite.

### 651–660 — Adaptive planning
- 651 KEEP — planning quality.
- 652 KEEP — critique.
- 653 KEEP — revision loop.
- 654 KEEP — uncertainty-aware decomposition.
- 655 KEEP — discovery tasks.
- 656 KEEP — dependency-risk analysis.
- 657 KEEP — critical path.
- 658 KEEP — dynamic reprioritization.
- 659 KEEP — scope drift.
- 660 CERTIFICATION — adaptive planning evaluation.

### 661–670 — Tool governance
- 661 KEEP — canonical tool contract.
- 662 KEEP — one tool registry.
- 663 KEEP — capability routing.
- 664 KEEP — authorization.
- 665 KEEP — dry run.
- 666 KEEP — idempotency.
- 667 KEEP — rate-limit handling.
- 668 MERGE — provenance uses canonical provenance/event lineage.
- 669 KEEP — failure taxonomy.
- 670 CERTIFICATION — governed tool execution suite.

### 671–680 — Portfolio coordination
- 671 KEEP — project relationships.
- 672 KEEP — cross-project dependencies.
- 673 KEEP — shared-resource arbitration.
- 674 KEEP — project priority arbitration.
- 675 MERGE — cross-project knowledge sharing must use the canonical knowledge/data governance policy.
- 676 MERGE — project isolation must use the canonical security/data boundary.
- 677 KEEP — portfolio scheduler.
- 678 KEEP — portfolio health.
- 679 CERTIFICATION — coordination suite.
- 680 CERTIFICATION — portfolio autonomy acceptance.

## Required dependency corrections

1. Transactional runtime state must precede advanced autonomy. GitHub remains an artifact/control-plane adapter, not the runtime database.
2. Model/provider abstraction precedes model routing optimization.
3. Tool authorization, identity and security policy precede broad external side effects.
4. Prompt/context trust boundaries precede ingestion of external content.
5. Sandbox enforcement precedes autonomous code execution.
6. Canonical knowledge/provenance precedes advanced memory reuse.
7. Generic planner/task contracts precede domain adapters.
8. Canonical event/control loop precedes continuous operation.
9. Human decision/approval contracts precede high-impact autonomous actions.
10. Certification follows implementation; it does not replace it.

## V1 boundary

Mandatory zero-cost single-user V1:
- local/self-hosted runtime
- existing model accounts as finite resources
- local/open-source tools where practical
- generic projects/tasks
- research, writing, software, business/operations adapters
- durable operational state
- governed tool execution
- security/privacy boundaries
- recovery/restart
- human approval for high-risk actions
- bounded continuous operation
- measurable evaluation

Deferred unless justified by later evidence:
- multi-user tenancy
- distributed orchestration
- leader election
- enterprise compliance
- large-scale cloud deployment
- paid-provider optimization

## Final engineering decision
The candidate count of 680 is **not** the implementation count. The correct next step is to construct the deduplicated implementation sequence from the canonical architecture above, then assign new task IDs only to genuinely distinct implementation units.

**No Tasks 681+ until that sequence is generated and reviewed.**
