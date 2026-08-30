# Backend Immo

Backend Java/Spring Boot du projet immobilier, structuré selon une architecture hexagonale.

## Architecture

```text
backend/
├── common/          # utilitaires et objets transverses
├── domain/          # modèle métier et règles de domaine
├── service/         # cas d'usage applicatifs
├── infrastructure/  # adaptateurs techniques (MongoDB, sécurité, etc.)
├── web/             # API REST et point d'entrée Spring Boot
├── pom.xml
├── mvnw
├── Dockerfile
└── .env.example
```

## Prérequis

- Java 25
- Maven wrapper (fourni)
- Docker (pour MongoDB et éventuellement la stack complète)

## Démarrage rapide

Depuis la racine du monorepo :

```bash
npm run dev:backend
```

Ou directement dans le backend :

```bash
cd backend
./mvnw -pl web -am spring-boot:run
```

L’API est ensuite accessible sur :

```text
http://localhost:8080
```

## Build

```bash
cd backend
./mvnw -pl web -am package -DskipTests
```

Le jar généré est produit dans :

```text
backend/web/target/web-0.0.1-SNAPSHOT.jar
```

## Variables d’environnement

Le backend attend par défaut cette configuration :

```env
SPRING_DATA_MONGODB_URI=mongodb://root:secretpassword@localhost:27017/immo?authSource=admin
SERVER_PORT=8080
```

Quand le projet est lancé via Docker Compose, la base MongoDB est fournie par le service `mongodb` du fichier racine.

## Docker

Le Dockerfile du backend construit le binaire Maven avant de lancer le jar Spring Boot :

```bash
cd /Users/js_rolland/dev/immo
docker compose up --build backend
```

## Points de vigilance

- Le point d’entrée Spring Boot est le module `web`.
- L’architecture hexagonale impose de maintenir la séparation entre métier, use cases et infrastructure.
- Les dépendances techniques doivent rester concentrées dans `infrastructure` et `web`.
