# AquaCentral

AquaCentral is a small aquarium management app. The idea is simple: keep track of your tanks and what lives in them, then later build reminders and stats around maintenance.

Right now the backend is in better shape than the frontend – it already knows about users, tanks and inhabitants, and exposes a basic REST API.

---

## Stack

- Java 17, Spring Boot 4
- Spring Data JPA, Spring Security, Flyway
- PostgreSQL (dev/prod), H2 in-memory (local/tests)
- Next.js + React + Tailwind (basic UI)
- Docker + docker-compose

---

## Layout

- `backend/` – Spring Boot API
  - `domain/` – entities (`User`, `Tank`, `Inhabitant`, enums)
  - `controller/` – REST controllers
  - `repository/` – Spring Data repositories
  - `config/` – security, seed data, helpers
  - `dto/` – request/response types
  - `resources/db/migration/` – Flyway migrations
- `frontend/` – Next.js app
- `infra/` – Docker and compose files

---

## Running the backend (local dev)

The `local` profile uses an in-memory H2 database and seeds a demo user, so you don't need Postgres running.

From `backend/`:

```bash
# Windows PowerShell
.\gradlew bootRun
```

What this does:

- Starts on `http://localhost:8080`
- Uses H2 in-memory
- Creates a user `local@aquacentral.dev` / `password` if there are no users yet

You can hit the health endpoint to check it's alive:

```bash
curl http://localhost:8080/api/health
```

---

## Running everything with Docker

From the repo root:

```bash
cd infra
docker-compose up --build
```

This brings up:

- `db` – Postgres 16
- `backend` – Spring Boot API (talking to `db`)
- `frontend` – Next.js app

You'll need to provide standard Postgres env vars (`POSTGRES_DB`, `POSTGRES_USER`, `POSTGRES_PASSWORD`) to compose, either via an `.env` file or your shell.

---

## API (high level)

### Health

- `GET /api/health` – Returns app name, current time and active Spring profile.

### Tanks

Each tank belongs to a user and is the root for the rest of the domain.

- `GET /api/tanks` – list tanks for the current user
- `GET /api/tanks/{id}` – get a single tank (404 if it doesn't belong to you)
- `POST /api/tanks` – create a tank

Example create payload:

```json
{
  "name": "75g Goldfish Tank",
  "type": "FRESHWATER",
  "volume": 75.0,
  "volumeUnit": "GALLONS",
  "description": "Goldfish display tank"
}
```

### Inhabitants

Inhabitants are fish/shrimp/plants/snails that live in a tank.

- Model, repository and database table are in place.
- Endpoints will live under `/api/tanks/{tankId}/inhabitants` (e.g. `POST` to add, `GET` to list). These are being wired up next.

---

## Security

- Spring Security is on.
- Public endpoints: `/api/health`, `/api/tanks`, `/api/tanks/**`, plus Swagger/OpenAPI (`/api-docs/**`, `/swagger-ui.html`, `/swagger-ui/**`).
- Everything else currently requires authentication (default login form). Passwords are stored with BCrypt.

Auth is intentionally very light for now; proper login/registration and roles will come later.

---

## Database migrations

Flyway keeps the schema in sync:

- `V1__create_users_table.sql` – users
- `V2__create_tanks_table.sql` – tanks (with `owner_id` → `users.id`)
- `V3__create_inhabitants_table.sql` – inhabitants (with `tank_id` → `tanks.id`)

On Postgres-backed profiles (`dev` / `prod`), migrations run automatically on startup.

---

## Status / roadmap

**Done:**

- User and tank model + migrations
- Tank API: create, list, get by id
- Inhabitant model, repository and schema
- Basic security configuration
- Local dev profile with seed user and H2

**Next:**

- Inhabitant API (create/list per tank)
- Maintenance tasks (templates, schedules, logs)
- Frontend screens for managing tanks and inhabitants
- Real authentication and user registration

PRs and ideas are welcome.
