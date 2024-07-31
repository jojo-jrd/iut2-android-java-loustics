package com.example.josseraj_ecole_des_loustics.bd;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.josseraj_ecole_des_loustics.R;

public class DatabaseLoustics {

    private static DatabaseLoustics instance;

    private AppDatabase appDatabase;

    private DatabaseLoustics(final Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "DataBaseLoustics").addCallback(roomDatabaseCallback).build();
    }

    public static synchronized DatabaseLoustics getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseLoustics(context);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {

        // Called when the database is created for the first time.
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            int ano = R.drawable.avataranonyme;
            db.execSQL("INSERT INTO compte (pseudo,nom,prenom,avatar) VALUES(\"Anonyme\", \"Anonyme\",\"Anonyme\","+ano+");");

            // Remplissage bdd capitale
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"France\", \"Paris\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Allemagne\", \"Berlin\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Suisse\", \"Berne\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Brésil\", \"Brasilia\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Belgique\", \"Bruxelles\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Argentine\", \"Buenos Aires\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Royaume-uni\", \"Londres\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Ukraine\", \"Kiev\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Russie\", \"Moscou\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Monaco\", \"Monaco\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Méxique\", \"Mexico\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Canada\", \"Ottawa\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Chine\", \"Pekin\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"États-unis\", \"Washington D.C.\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Japon\", \"Tokyo\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Algérie\", \"Alger\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Maroc\", \"Rabat\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Italie\", \"Rome\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Danemark\", \"Copenhague\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Portugal\", \"Lisbonne\");");
            db.execSQL("INSERT INTO capitale (pays,capitale) VALUES(\"Luxembourg\", \"Luxembourg\");");

            // Insertion question conjugaison
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(1, \"Parmi ces verbes, lequel fait partie du 1er groupe ?\",\"faire\",\"aller\",\"venir\",\"Français - Conjugaison\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(2, \"Quel est le verbe conjugué à la 3e personne du pluriel ?\",\"dormaient\",\"allait\",\"jardines\",\"Français - Conjugaison\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(3, \"Présent du verbe aimer - vous ?\",\"aimez\",\"aimes\",\"aimons\",\"Français - Conjugaison\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(4, \"Imparfait du verbe être - ils ?\",\"étaient\",\"étions\",\"étais\",\"Français - Conjugaison\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(5, \"Imparfait du verbe avoir - ils ?\",\"avaient\",\"avait\",\"étaient\",\"Français - Conjugaison\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(6, \"Présent du verbe sentir - ils ?\",\"sentent\",\"sente\",\"sentaient\",\"Français - Conjugaison\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(7, \"Imparfait du verbe finir - vous ?\",\"finissiez\",\"finit\",\"finissons\",\"Français - Conjugaison\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(8, \"Futur simple du verbe finir - il ?\",\"finira\",\"finiront\",\"iront\",\"Français - Conjugaison\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(9, \"Présent du verbe être - je ?\",\"suis\",\"est\",\"vivre\",\"Français - Conjugaison\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(10, \"Futur simple du verbe aimer - ils ?\",\"aimeront\",\"aimaient\",\"aiment\",\"Français - Conjugaison\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(11, \"Imparfait du verbe aimer - tu ?\",\"aimais\",\"aimaient\",\"étais\",\"Français - Conjugaison\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(12, \"Futur simple du verbe avoir - ils ?\",\"auront\",\"aimont\",\"voulaient\",\"Français - Conjugaison\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(13, \"Imparfait du verbe finir - nous ?\",\"finissions\",\"finirons\",\"aimions\",\"Français - Conjugaison\");");

            // Insertion question grammaire - facile
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(14, \"Joseph ..... Michel ont dormi hier soir.\",\"et\",\"est\",\"sont\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(15, \"Jean ..... mangé hier soir.\",\"a\",\"à\",\"est\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(16, \"Robert dort sur ..... lit.\",\"son\",\"à\",\"sont\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(17, \"..... fait des mathématiques ..... du Français.\",\"on / et\",\"on / est\",\"ont / est\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(18, \"Je vais ... l'école demain.\",\"à\",\"a\",\"est\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(19, \"Hier, je suis allé .... la patinoire.\",\"à\",\"a\",\"son\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(20, \"Ces fleurs ..... jolies.\",\"sont\",\"son\",\"et\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(21, \"Les fleurs ..... besoin d'eau.\",\"ont\",\"est\",\"sont\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(22, \"Jean ..... Roger ..... sortis hier à 23h.\",\"et / sont\",\"est / sont\",\"est / son\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(23, \"..... va au travail.\",\"on\",\"et\",\"son\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(24, \"L'arbre ..... grand.\",\"est\",\"et\",\"sont\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(25, \"Leo ..... allé à l'université.\",\"est\",\"et\",\"sont\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO question(id,question,bonneReponse,autreReponse1,autreReponse2,theme) VALUES(26, \"Il ..... parti tout à l'heure.\",\"est\",\"et\",\"ont\",\"Français - Grammaire\");");

            // Insertion textes à trou grammaire - moyen
            db.execSQL("INSERT INTO texte_a_trou(id,partiePhrase1,reponse1,partiePhrase2,reponse2,partiePhrase3,theme) VALUES(1, \"Les enfants \",\"sont\",\" entrain de jouer dans la cours de récréation.\",\"NULL\",\"NULL\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO texte_a_trou(id,partiePhrase1,reponse1,partiePhrase2,reponse2,partiePhrase3,theme) VALUES(2, \"Les élèves se \",\"sont\",\" balladés en forêt.\",\"NULL\",\"NULL\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO texte_a_trou(id,partiePhrase1,reponse1,partiePhrase2,reponse2,partiePhrase3,theme) VALUES(3, \"Juliette a oublié \",\"son\",\" cartable\",\"NULL\",\"NULL\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO texte_a_trou(id,partiePhrase1,reponse1,partiePhrase2,reponse2,partiePhrase3,theme) VALUES(4, \"Je vais \",\"à\",\" Paris demain. Jean \",\"a\",\" tout organisé.\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO texte_a_trou(id,partiePhrase1,reponse1,partiePhrase2,reponse2,partiePhrase3,theme) VALUES(5, \"Julien \",\"a\",\" appris sa leçon \",\"à\",\" l'école.\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO texte_a_trou(id,partiePhrase1,reponse1,partiePhrase2,reponse2,partiePhrase3,theme) VALUES(6, \"Léo \",\"et\",\" Mathilde jouent aux billes \",\"et\",\" aux échecs ensemble.\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO texte_a_trou(id,partiePhrase1,reponse1,partiePhrase2,reponse2,partiePhrase3,theme) VALUES(7, \"Son chien \",\"est\",\" joueur \",\"et\",\" très câlin.\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO texte_a_trou(id,partiePhrase1,reponse1,partiePhrase2,reponse2,partiePhrase3,theme) VALUES(8, \"A l'école, \",\"on\",\" fait des mathématiques.\",\"NULL\",\"NULL\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO texte_a_trou(id,partiePhrase1,reponse1,partiePhrase2,reponse2,partiePhrase3,theme) VALUES(9, \"Hier, \",\"on\",\" a entendu tout ce qu'ils \",\"ont\",\" dit.\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO texte_a_trou(id,partiePhrase1,reponse1,partiePhrase2,reponse2,partiePhrase3,theme) VALUES(10, \"Les adultes \",\"sont\",\" méchants, ils \",\"ont\",\"mangé tous les bonbons.\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO texte_a_trou(id,partiePhrase1,reponse1,partiePhrase2,reponse2,partiePhrase3,theme) VALUES(11, \"Aymeric \",\"et\",\" Rémy vont \",\"à\",\" la piscine ensemble.\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO texte_a_trou(id,partiePhrase1,reponse1,partiePhrase2,reponse2,partiePhrase3,theme) VALUES(12, \"Jordan \",\"a\",\" travaillé dûr avec \",\"son\",\" talent.\",\"Français - Grammaire\");");
            db.execSQL("INSERT INTO texte_a_trou(id,partiePhrase1,reponse1,partiePhrase2,reponse2,partiePhrase3,theme) VALUES(13, \"Les loups \",\"sont\",\" affamés.\",\"NULL\",\"NULL\",\"Français - Grammaire\");");

            // Remplissage de la bdd avec les drapeaux des pays
            db.execSQL("INSERT INTO drapeau(pays,drapeau) VALUES(\"Allemagne\","+R.drawable.flag_allemagne+");");
            db.execSQL("INSERT INTO drapeau(pays,drapeau) VALUES(\"Belgique\","+R.drawable.flag_belgique+");");
            db.execSQL("INSERT INTO drapeau(pays,drapeau) VALUES(\"Brésil\","+R.drawable.flag_bresil+");");
            db.execSQL("INSERT INTO drapeau(pays,drapeau) VALUES(\"Corée du sud\","+R.drawable.flag_coree_du_sud+");");
            db.execSQL("INSERT INTO drapeau(pays,drapeau) VALUES(\"Égypte\","+R.drawable.flag_egypte+");");
            db.execSQL("INSERT INTO drapeau(pays,drapeau) VALUES(\"Espagne\","+R.drawable.flag_espagne+");");
            db.execSQL("INSERT INTO drapeau(pays,drapeau) VALUES(\"États-unis\","+R.drawable.flag_etats_unis+");");
            db.execSQL("INSERT INTO drapeau(pays,drapeau) VALUES(\"Inde\","+R.drawable.flag_inde+");");
            db.execSQL("INSERT INTO drapeau(pays,drapeau) VALUES(\"Maroc\","+R.drawable.flag_maroc+");");
            db.execSQL("INSERT INTO drapeau(pays,drapeau) VALUES(\"Mexique\","+R.drawable.flag_mexique+");");
            db.execSQL("INSERT INTO drapeau(pays,drapeau) VALUES(\"Norvege\","+R.drawable.flag_norvege +");");
            db.execSQL("INSERT INTO drapeau(pays,drapeau) VALUES(\"Portugal\","+R.drawable.flag_portugal+");");
            db.execSQL("INSERT INTO drapeau(pays,drapeau) VALUES(\"Suède\","+R.drawable.flag_suede+");");



        }
    };
}
