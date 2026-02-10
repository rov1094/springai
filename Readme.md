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


# Prompt Template and Stuffing
## 1 Prompt Template :
- Created by file name {file_name}.st under resource
- Passed in service as an rescource
- `@Value("classpath: prompts/order_system_template.st)
    private Resource orderSystemTemplate;`
- And above resource is passed in system() or user() message roles of a prompt

## 2 Prompt Stuffing :
- Loads policy of the system/application, just that to avoid generic messages given by the llm
- This is used for limited use case, for files based or large data set based we need to use RAG to load the policy