//SCANNER PERMET DE DEMANDER DES DONNEE A UTILISATEUR
import java.util.Scanner;
//RANDOM PERMET DE GENERER DES NOMBRES ALEATOIRE
import java.util.Random;
//ARRAYLIST PERMET DE STOCKER DES DONN2ES DYNAMIQUE
import java.util.ArrayList;
//
import java.util.Collections;
//permet de creer et de modifier des fichiers
import java.util.List;
//
import java.io.FileWriter;
//
import java.io.IOException;
//
import java.io.File;

public class Main {

    // Choisir une direction aléatoire

    /**
     * direction aleatoire : permet de genere une direction aletoire pour la generation du labyrinthe
     * @return
     */
    public static int[][] direction_aleatoire() {
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        List<int[]> directionsList = new ArrayList<>();
        Collections.addAll(directionsList, direction);
        Collections.shuffle(directionsList);  // Mélange les directions
        direction = new int[directionsList.size()][2];
        directionsList.toArray(direction);  // Convertit la liste mélangée en tableau
        return direction;
    }

    // THIMEO DEBUT
    // Demande la taille du labyrinthe

    /**
     * taille() : permet de demander a l'utilistaeur de choisir la taille du l'abyrinthe
     * via boucle while
     * @return
     */
    public static int taille() {
        //CREER LE SCANNER
        Scanner scanner = new Scanner(System.in);
        int nombre;
        //DEMANDE UTILISATEUR
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("--- Veuillez choisir la taille de votre labyrinthe entre 5 et 50 (cette donnée doit être impair) ---");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        //BOUCLE INFINIE
        while (true) {
            //VERIF SI LE NOMBRE EST UN ENTIER
            if (scanner.hasNextInt()) {
                nombre = scanner.nextInt();
                //VERIF SI LE NOMBRE EST PAIR
                if (nombre % 2 == 0) {
                    System.out.println("---------------------------------");
                    System.out.println("-- Le nombre doit être impair. --");
                    System.out.println("---------------------------------");
                    //VERIF SI LE NOMBRE REPOND AU CRITERE
                } else if (nombre >= 5 && nombre <= 50) {
                    System.out.println("--- Taille du labyrinthe : " + nombre + " ---");
                    break; //FIN BOUCLE
                    //VEIR SI LE NOMBRE EST HORS VARIABLE
                } else {
                    System.out.println("------------------------------------------");
                    System.out.println("--- Le nombre doit être entre 5 et 50. ---");
                    System.out.println("------------------------------------------");
                }
                //SI LENTREE NEST PAS ENTIER
            } else {
                System.out.println("--------------------------------------------------------");
                System.out.println("--- Ce nombre n'est pas valide, veuillez réessayer : ---");
                System.out.println("--------------------------------------------------------");
                scanner.next();  //NON VALIDE DONC NOUCLE DEBUT
            }
        }
        //RETOUR A LA TAILLE VALIDEE DU LABY (ENCORE DU MAL A COMPRENDRE)
        return nombre;
    }

    /**
     * entree() : demande utilisateur position entree labyrinthe
     * via boucle while
     * @return
     */
    // Demander à l'utilisateur où il veut situer l'entrée
    public static int entree(int nombre) {
        //CREER UN SCANNER
        Scanner scannerEntree = new Scanner(System.in);
        int start;

        //AFFICHE MESSAGE DEMANDE
        System.out.println("********************************************************************************************");
        System.out.println("*** Veuillez définir l'emplacement latéral de l'entrée (entre 1 , " + (nombre - 2) + " et doit être pair) ***");
        System.out.println("********************************************************************************************");
        //BOUCLE
        while (true) {
            //VERIF SI ENTIER
            if (scannerEntree.hasNextInt()) {
                start = scannerEntree.nextInt();
                //VERIF
                if (start > 1 && start < nombre - 1 && start % 2 == 0) {
                    System.out.println("Position de l'entrée validée : " + start);
                    break; //FIN BOUCLE
                    //SI LIMITE DEPASSER
                } else {
                    System.out.println("****************************************************************************");
                    System.out.println("*** L'entrée ne peut pas être en 1 ou " + (nombre - 1) + " ou impair. Réessayez. *****");
                    System.out.println("****************************************************************************");
                }
                //SI NON ENTIER
            } else {
                System.out.println("##########################################################");
                System.out.println("### Entrée invalide. Veuillez entrer un nombre entier. ###");
                System.out.println("##########################################################");
                scannerEntree.next();
            }
        }
        //RETOUR POSITION VALIDEE ENTREE
        return start;
    }

    /**
     * fin(int nombre) : demande emplacement fin
     * via boucle while
     * @param
     * @return
     */
    // Demander à l'utilisateur où il veut situer la sortie
    public static int fin(int nombre) {
        //CREE SCANNER
        Scanner scannerEntree = new Scanner(System.in);
        int end;
        //DEMANDE UTILISATEUR
        System.out.println("************************************************************************************************************");
        System.out.println("*** Veuillez définir l'emplacement de la sortie (entre 1(non inclus), " + (nombre - 2) + " et doit être pair) ***");
        System.out.println("************************************************************************************************************");
        //BOUCLE
        while (true) {
            //VERIF ENTIER
            if (scannerEntree.hasNextInt()) {
                end = scannerEntree.nextInt();
                //VERIF POSITION VALIDE
                if (end > 0 && end < nombre - 1 && end % 2 == 0) {
                    System.out.println("Position de la sortie validée : " + end);
                    break;//SORT BOUCLE
                    //SI POSITION NON VALIDE
                } else {
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.println("--- La sortie ne peut pas être en 0, impair ou " + (nombre - 1) + ". Réessayez. ---");
                    System.out.println("-----------------------------------------------------------------------------------");
                }
                //SI NON ENTIER
            } else {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("+++ Entrée invalide. Veuillez entrer un nombre entier. +++");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                scannerEntree.next();
            }
        }
        //RETOUR POSITION VALIDE SORTIE
        return end;
    }

    /**
     * generateLab() : generation labyrinthe
     * boucle for
     * @param tab
     * @param posX
     * @param posY
     * @param lignes
     * @param colonnes
     * @param verifpassage
     */
    // Générer le labyrinthe

    public static int compteur_visite_une_case;
    public static int compteur_itérations;
    static int[]  generateLab(String[][] tab, int posX, int posY, int lignes, int colonnes, boolean[][] verifpassage) {
        tab[posX][posY] = "   ";
        verifpassage[posX][posY] = true;
        compteur_itérations++;
        // Générer une direction aléatoire

        for (int i = 0; i < 4; i++) {
            int[] direction = direction_aleatoire()[i];
            int x_nouveau = posX + direction[0] * 2;
            int y_nouveau = posY + direction[1] * 2;
            if (x_nouveau >= 0 && x_nouveau < lignes
                    && y_nouveau >= 0 && y_nouveau < colonnes
                    && !verifpassage[x_nouveau][y_nouveau]) {
                tab[posX + direction[0]][posY + direction[1]] = "   ";
                compteur_visite_une_case++;
                generateLab(tab, x_nouveau, y_nouveau, lignes, colonnes, verifpassage);
            }
        }
        for (int i = 1; i < tab.length; i = i + 2) {
            for (int j = 1; j < tab[i].length; j = j + 2) {
                if (tab[i][j] == " # ") {
                    generateLab(tab, i, j, lignes, colonnes, verifpassage);
                }
            }

        }
        int [] compteur = {compteur_itérations,compteur_visite_une_case};
        return compteur;

    }

    /**
     * demandersauvegarde() : demande utilisateur oui/non sauvegarde
     * via boucle if
     * @param tableau
     * @param lignes
     * @param colonnes
     */
    // Demander si l'utilisateur souhaite sauvegarder le labyrinthe
    public static void demanderSauvegarde(String[][] tableau, int lignes, int colonnes) {
        //CREE SCANNER
        Scanner scanner = new Scanner(System.in); //system.in = flux d'entrée
        //DEMANDE UTILISATEUR
        System.out.println("**************************************************************************");
        System.out.println("*** Voulez-vous sauvegarder le labyrinthe dans un fichier ? (oui/non) ****");
        System.out.println("**************************************************************************");
        String reponse = scanner.nextLine(); //nextLine permet de prendre en compte tout les carractere dit avant la toucher entrée

        //SI LA PERSONNE DIT OUI - APPELLE SAUVEGARDE
        if (reponse.equalsIgnoreCase("oui")) {
            sauvlaby(tableau, lignes, colonnes); // Sauvegarder le labyrinthe
        } else {
            //NE SAUVEGARDE PAS
            System.out.println("---------------------------------------------");
            System.out.println("--- Le labyrinthe ne sera pas sauvegardé. ---");
            System.out.println("---------------------------------------------");
        }
    }


    /**
     * getNextLabyrintheFileName() :
     * @return
     */
        // Fonction pour générer un nom de fichier unique avec un numéro incrémenté
        public static String getNextLabyrintheFileName() {
            File directory = new File("./Labgen");
            if (!directory.exists()) {
                directory.mkdirs(); // Créer le répertoire s'il n'existe pas
            }

            // Récupérer tous les fichiers labyrinthes existants
            File[] files = directory.listFiles((dir, name) -> name.startsWith("labyrinthe") && name.endsWith(".txt"));

            int nextNumber = 1; // Commence à 1
            if (files != null && files.length > 0) {
                // Parcourir les fichiers pour trouver le dernier numéro
                for (File file : files) {
                    String fileName = file.getName();
                    String numberPart = fileName.replace("labyrinthe_", "").replace(".txt", "");
                    try {
                        int number = Integer.parseInt(numberPart);
                        if (number >= nextNumber) {
                            nextNumber = number + 1; // Incrémenter le plus grand numéro
                        }
                    } catch (NumberFormatException ignored) {
                        // Ignorer si le fichier n'a pas de numéro valide
                    }
                }
            }

            // Retourner le prochain nom de fichier
            return directory.getAbsolutePath() + "/labyrinthe_" + nextNumber + ".txt";
        }

        // Fonction modifiée pour sauvegarder le labyrinthe dans un fichier avec un numéro incrémenté

    /**
     * sauvlaby : enregistre labyrinthe
     * @param tableau
     * @param lignes
     * @param colonnes
     */
        public static void sauvlaby(String[][] tableau, int lignes, int colonnes) {
            // Obtenir un nom de fichier avec numéro incrémenté
            String nom_fichier = getNextLabyrintheFileName();

            try {
                // Sauvegarder le labyrinthe dans le fichier
                FileWriter writer = new FileWriter(nom_fichier, true);
                writer.write("Nouveau Labyrinthe :\n");

                for (int i = 0; i < lignes; i++) {
                    for (int j = 0; j < colonnes; j++) {
                        writer.write(tableau[i][j]);
                    }
                    writer.write("\n"); // Saut de ligne après chaque ligne du labyrinthe
                }
                writer.write("\n"); // Saut de ligne pour séparer les labyrinthes
                writer.close();
                System.out.println("Labyrinthe sauvegardé avec succès dans le fichier : " + nom_fichier);
            } catch (IOException e) {
                System.out.println("Une erreur s'est produite lors de la sauvegarde.");
                e.printStackTrace();
            }
        }

    // Récupérer tous les fichiers labyrinthes dans le répertoire "Labgen"
    public static File[] getAlllabyrintheFiles() {
        // Récupérer le répertoire "Labgen"
        File directory = new File("./Labgen");

        // Vérifier si le répertoire existe
        if (!directory.exists()) {
            System.out.println("******************************************");
            System.out.println("*** Le répertoire Labgen n'existe pas. ***");
            System.out.println("******************************************");
            return new File[0]; // Retourne un tableau vide si le répertoire n'existe pas
        }

        // Filtrer les fichiers qui commencent par "labgen" et se terminent par ".txt"
        File[] labyrintheFiles = directory.listFiles((dir, name) -> name.startsWith("labgen") && name.endsWith(".txt"));//

        return labyrintheFiles; // Retourner les fichiers labyrinthes trouvés
    }


    // Résoudre le labyrinthe

    /**
     * solveractivate : active demande utilisateur solution
     * boucle while
     * @return
     */
    public static boolean solverActivate () {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("--------------------------------------------------");
            System.out.println("--- Voulez vous résoudre le labyrinthe ? (Y/N) ---");
            System.out.println("--------------------------------------------------");
            String answer = scanner.nextLine().trim().toLowerCase();

            // Si l'utilisateur veut activé le solver
            if (answer.equals("Y") || answer.equals("y")) {
                System.out.println("*********************");
                System.out.println("*** Solver Activé ***");
                System.out.println("*********************");
                return true;
            }
            // Si l'utilisateur veut désactivé le solver
            else if (answer.equals("N") || answer.equals("n")) {
                System.out.println("************************");
                System.out.println("*** Solver Désactivé ***");
                System.out.println("************************");
                return false;
            } else {
                System.out.println("Hein ?");

            }

        }

    }

    //MATIAS FIN

    //IDRIS DEBUT

    /**
     * solvemaze : genere la solution,
     * boucle if
     * @param maze
     * @param x
     * @param y
     * @param endX
     * @param endY
     * @return
     */
    public static boolean solveMaze(String[][] maze, int x, int y, int endX, int endY) {
        if (x == endX && y == endY) {
            return true; // Arrivée atteinte
        }

        if (isSafe(maze, x, y)) {
            maze[x][y] = " V "; // Marquer le chemin
            // Explorer les quatre directions
            if (solveMaze(maze, x + 1, y, endX, endY) ||
                    solveMaze(maze, x - 1, y, endX, endY) ||
                    solveMaze(maze, x, y + 1, endX, endY) ||
                    solveMaze(maze, x, y - 1, endX, endY)) {
                return true; // Chemin trouvé
            }

            maze[x][y] = "   "; // Retirer la marque si chemin non valide
        }
        return false;
    }

    // Vérifier si la position est sûre

    /**
     * issafe : sauvegarde
     * @param maze
     * @param x
     * @param y
     * @return
     */
    public static boolean isSafe(String[][] maze, int x, int y) {
        return (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y].equals("   "));
    }

    public static void main(String[] args) {

        long tps1 = System.currentTimeMillis();

        // Taille du labyrinthe
        int nombre = taille();

        // Définir l'emplacement de départ
        int start = entree(nombre);

        // Définir l'emplacement de fin
        int end = fin(nombre);

        int lignes = nombre;
        int colonnes = nombre;
        String[][] tableau = new String[lignes][colonnes];


        // Initialiser tableau des passages
        boolean[][] verifpassage = new boolean[lignes][colonnes];

        // Initialiser le tableau et la vérification des passages
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                verifpassage[i][j] = false;
            }
        }

        // Remplir le tableau avec des murs
        for (int x = 0; x < tableau.length; x++) {
            for (int y = 0; y < tableau[x].length; y++) {
                tableau[x][y] = " # ";
            }
        }

        // Générer le labyrinthe à partir de la position (1,1)
        int [] compteur = generateLab(tableau, 1, 1, lignes, colonnes, verifpassage);

        // Placer l'entrée
        int x_entre = 0;
        int y_entre = start - 1;
        tableau[x_entre][y_entre] = " E ";

        // Placer la sortie
        int x_sortie = nombre - 1;
        int y_sortie = end - 1;
        tableau[x_sortie][y_sortie] = " S ";

        long tps2 = System.currentTimeMillis();

        long tps_execution = tps2 - tps1;

        System.out.println("Temps d'éxecution " + tps_execution + " ms");
        System.out.println("Compteur d'itérations "+compteur[0]);
        System.out.println("Compteur de visite d'une case "+compteur[1]);



        // Résoudre le labyrinthe
        if (solverActivate() == true) {
            if (solveMaze(tableau, 1, start - 1, nombre - 1, end - 1)) {
                System.out.println("Le chemin a été trouvé !");
                System.out.println("Labyrinthe avec le chemin :");
                for (int i = 0; i < tableau.length; i++) {
                    for (int j = 0; j < tableau[i].length; j++) {
                        System.out.print(tableau[i][j]);
                    }
                    System.out.println();
                }
            } else {
                System.out.println("Aucun chemin trouvé.");
            }
        }
        /*
        int compteur = 1;
        String nom_dossier = "Labgen";
        while (new File(nom_dossier).exists()) {
            compteur++;
            nom_dossier += compteur; // Ajouter directement le compteur pour les nombres 10 et plus
        }
        nom_dossier = nom_dossier.substring(0, nom_dossier.length() - 1);
        File dossier = new File(nom_dossier);
        if (!dossier.exists()) {
            if (dossier.mkdir()) {
                System.out.println("Dossier 'labgen' créé avec succès.");
            } else {
                System.out.println("Erreur lors de la création du dossier 'labgen'.");
            }
        } else {
            System.out.println("Le dossier 'labgen' existe déjà.");
        }

        try {
            FileWriter writer = new FileWriter("");
            for (int i = 0; i < lignes; i++) {
                for (int j = 0; j < colonnes; j++) {
                    writer.write(tableau[i][j]);
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Erreur");
            e.printStackTrace();
        }
        */

        int[] joueur = {x_entre, y_entre};
        Scanner scanner = new Scanner(System.in);
        while (joueur[0] != x_sortie || joueur[1] != y_sortie) {
            tableau[joueur[0]][joueur[1]] = " J ";
            for (int i = 0; i < tableau.length; i++) {
                for (int j = 0; j < tableau[i].length; j++) {
                    System.out.print(tableau[i][j]);
                }
                System.out.println();
            }
            tableau[joueur[0]][joueur[1]] = "   ";

            System.out.println("A vous de jouer");
            String input = scanner.nextLine();  // Récupère la ligne entière

            if (input.equalsIgnoreCase("z")) {
                int[] new_co = {joueur[0] - 1, joueur[1]};
                if (new_co[0] >= 0 && new_co[0] < lignes
                        && new_co[1] >= 0 && new_co[1] < colonnes
                        && tableau[new_co[0]][new_co[1]] != " # ") {
                    joueur[0] -= 1;
                }

            }
            if (input.equalsIgnoreCase("s")) {
                int[] new_co = {joueur[0] + 1, joueur[1]};// Vérifie si l'utilisateur a pressé "Z"
                if (new_co[0] >= 0 && new_co[0] < lignes
                        && new_co[1] >= 0 && new_co[1] < colonnes
                        && tableau[new_co[0]][new_co[1]] != " # ") {
                    joueur[0] += 1;
                }
            }
            if (input.equalsIgnoreCase("d")) {
                int[] new_co = {joueur[0], joueur[1] + 1};// Vérifie si l'utilisateur a pressé "Z"
                if (new_co[0] >= 0 && new_co[0] < lignes
                        && new_co[1] >= 0 && new_co[1] < colonnes
                        && tableau[new_co[0]][new_co[1]] != " # ") {
                    joueur[1] += 1;
                }
            }
            if (input.equalsIgnoreCase("q")) {
                int[] new_co = {joueur[0], joueur[1] - 1};
                if (new_co[0] >= 0 && new_co[0] < lignes
                        && new_co[1] >= 0 && new_co[1] < colonnes
                        && tableau[new_co[0]][new_co[1]] != " # ") {
                    joueur[1] -= 1;
                }
            }
            if (joueur[0] == x_sortie && joueur[1] == y_sortie) {
                System.out.println("Félicitations, vous avez trouvé la sortie !");
                break;
            }

        }

        demanderSauvegarde(tableau, lignes, colonnes);
        scanner.close();
    }
}




        // Affichage du tableau (c'est grace a Ines)