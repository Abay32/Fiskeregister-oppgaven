# Fiskeregister

## Hva er dette?
En demo-applikasjon for å administrere et fiskeregister. Backend er skrevet i Spring Boot med 
postgresql for datalagering og H2 database for test. Frontend er et enkelt React-oppsett som bruker REST API-et.

## Kjøre lokalt




### Backend
1. Bygg og start:

`mvn clean package 
mvn spring-boot:run`

Appen kjører på `http://localhost:8081`

### Frontend (React)
1. Gå til `fiskeregister-frontend` mappen.
2. Installer:
   `npm install
   npm start`

3. For backend, bruk `"proxy": "http://localhost:8081"` i `postman/swagger`.

## Endepunkter (eksempel)
- `GET /api/v1/fish` — hent alle fisker 
- `POST /api/v1/fish/create` — opprett fisk (JSON body)
- `PUT /api/v1/fish/{id}/update` — oppdater fisk
- `DELETE /api/v1/fish/{id}/delete` — slett fisk

## Kodearkitektur og designvalg
- **Layered architecture:** controller → service → repository 
- **DTO / Entity-separasjon:** for fleksibilitet og for å unngå at DB-entiteter lekker ut i API
- **Validering:** Jakarta Bean Validation (`@NotBlank`, `@Positive`) i DTO
- **Feilhåndtering:** `@ControllerAdvice` for konsistente HTTP-responser ved feil
- **H2:** In-memory DB for enkel testing

## Testing-verktøy
- Backend: JUnit 5, Spring Boot Test, MockMvc. Inkluderer både validerings- og integrasjonstester.
- 
- TODO: Frontend (valgfritt): React Testing Library / Jest for komponenttester.

## Forbedringer (hvis mer tid)
- Legge til sorting og filtrering (f.eks. by species)
- Autentisering/autorisering (f.eks. Basic, OAuth2)


