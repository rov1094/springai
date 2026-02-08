# 🏥 Insurance AI Assistant – Prompt Flow

This project demonstrates using **system roles** and a **predefined user message**
to safely power multiple insurance assistants with a single LLM.

---

## 🔄 Flow

1. User enters a question
2. User input is wrapped in a predefined user message
3. System roles are attached based on assistant type
4. LLM generates the response

---

## 1️⃣ Predefined User Message

- User input is treated as **untrusted**
- Any instructions inside user input are ignored
- Prompt injection attempts are neutralized

Used for **all assistants**.

---

## 2️⃣ System Roles

### S1 — Compliance & Legal Guardrail
- No claim approval or denial
- No legal advice
- No policy override
- High-level explanations only

### S2 — Insurance Domain Expert
- Explains policies and coverage
- Simple, customer-friendly language
- Educational responses

---

## 3️⃣ Assistants

### 🤖 Assistant A (Customer-Facing)
- System Roles: **S1 + S2**
- Used in public insurance app
- Safe and compliant

### 🤖 Assistant B (Internal Use)
- System Roles: **S2 only**
- Used by insurance staff
- Not customer-facing

---

## 🧠 Key Idea

Same LLM + same user input  
Different system roles → different behavior

System roles enforce rules.  
User messages reinforce safety.
