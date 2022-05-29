package com.example.codigo;

import com.example.codigo.clases.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static com.example.codigo.Conexion.conexion;

public class SpotifyController implements Initializable {

    //vistas
    @FXML
    public AnchorPane mainView;
    @FXML
    public AnchorPane buscarArtistaUnico;
    @FXML
    public AnchorPane vistaPlaylistUnica;
    @FXML
    public AnchorPane vistaAlbumsGeneral;
    @FXML
    public AnchorPane vistaAlbumUnico;
    @FXML
    public AnchorPane vistaArtistasGeneral;
    @FXML
    public HBox hBoxCancionInferior;
    @FXML
    public AnchorPane vistaRecentlyPlayed;
    @FXML
    public AnchorPane vistaYourPl;
    @FXML
    public AnchorPane vistaPlaylistsGeneral;
    @FXML
    public AnchorPane vistaCrearPlaylist;
    @FXML
    public AnchorPane vistaPlaylistsPatrocinadas;
    @FXML
    public AnchorPane vistaPodcasts;
    @FXML
    public AnchorPane vistaPodcastUnico;


    //tablas y columnas
    @FXML
    public TableView <Album>tablaAlbums;
    @FXML
    public TableColumn <Album, String> tituloAlbum;
    @FXML
    public TableColumn <Album, String> anyoAlbum;
    //OTRA TABLA
    @FXML
    public TableView<Album> albumArtista;
    @FXML
    public TableColumn<Album, String> tituloAlbumArtista;
    @FXML
    public TableColumn<Album, String> anyoAlbumArtista;
    //OTRA TABLA
    @FXML
    public TableView<Cancion> cancionesArtista;
    @FXML
    public TableColumn<Cancion, String> tituloCancionesArtista;
    //OTRA TABLA
    @FXML
    public TableView<Cancion> tablaCancionesAlbumUnico;
    @FXML
    public TableColumn<Cancion, String> columnaCancionesAlbumUnico;
    //OTRA TABLA
    @FXML
    public TableView<Artista> tablaArtistasSeguidos;
    @FXML
    public TableColumn<Artista, String> nombreTablaArtistasSeguidos;
    @FXML
    public TableColumn<Artista, ImageView> imagenTablaArtistasSeguidos;
    //OTRA TABLA
    @FXML
    public TableView<Cancion> tablaCancionesFav;
    @FXML
    public TableColumn<Cancion, String> tituloTablaCancionesFav;
    //OTRA TABLA
    @FXML
    public TableView<Artista> tablaArtistasCompleta;
    @FXML
    public TableColumn<Artista, String> nombreTablaArtistasCompleta;
    @FXML
    public TableColumn <Artista, ImageView> imagenTablaArtistasCompleta;
    //OTRA TABLA
    @FXML
    public TableView<Artista> tablaArtistaPropietarioAlbum;
    @FXML
    public TableColumn<Artista, String> columnaNombreArtistaPropietarioAlbum;
    @FXML
    public TableColumn <Artista, ImageView> columnaImgArtistaPropietarioAlbum;
    //OTRA TABLA
    @FXML
    public TableView<Cancion> tablaRecentlyPlayed;
    @FXML
    public TableColumn<Cancion, String> cancionesRecentlyPlayed;
    //OTRA TABLA
    @FXML
    public TableView<Playlist> tablaPlaylistsCreadas;
    @FXML
    public TableColumn<Playlist, String> nombrePlCreada;
    //OTRA TABLA
    @FXML
    public TableView<Playlist> tablaPlaylistsSeguidas;
    @FXML
    public TableColumn<Playlist, String> nombrePlSeguida;
    //OTRA TABLA
    @FXML
    public TableView<Playlist> tablaTodasPlaylists;
    @FXML
    public TableColumn<Playlist, String> titutloTodasPl;
    //OTRA TABLA
    @FXML
    public TableView<Playlist> tablaPlEliminadas;
    @FXML
    public TableColumn<Playlist, String> tituloPlEliminadas;
    //OTRA TABLA
    @FXML
    public TableView<Cancion> tablaCancionesPlaylistUnica;
    @FXML
    public TableColumn<Cancion, String> cancionesPlaylistUnica;
    //OTRA TABLA
    @FXML
    public TableView<Playlist> tablaPlPatrocinadas;
    @FXML
    public TableColumn<Playlist, String> tituloPlPatrocinadas;
    //OTRA TABLA
    @FXML
    public TableView<Podcast> tablaTodosPodcasts;
    @FXML
    public TableColumn<Podcast, String> tituloTodosPodcasts;
    @FXML
    public TableColumn<Podcast, Integer> anyoPodcastTodos;
    //OTRA TABLA
    @FXML
    public TableView<Podcast> tablaPodcastsSeguidos;
    @FXML
    public TableColumn<Podcast, String> tituloPodcastsSeguidos;
    @FXML
    public TableColumn<Podcast, Integer> anyoPodcastSeguido;
    //OTRA TABLA
    @FXML
    public TableView<Capitulo> tablaPodcastUnico;
    @FXML
    public TableColumn<Capitulo, String> capitulosPodcastUnico;
    @FXML
    public TableColumn<Capitulo, Integer> duracionPodcastUnico;



    //listas
    ObservableList<Album> listaAlbum;
    ObservableList<Cancion> listaCancionesAlbumUnico;
    public static ArrayList<Artista> artistList;
    ObservableList<Artista> listaArtistasSeguidos;
    ObservableList<Cancion> listaCancionesFav;
    ObservableList<Artista> listaArtistasCompleta;
    ObservableList<Cancion> listaCancionesArtista;
    ObservableList<Album> listaAlbumArtista;
    ObservableList<Artista> listaArtistaPropietarioAlbum;
    ObservableList<Cancion> listaRecentlyPlayed;
    ObservableList<Playlist> listaPlaylistsCreadas;
    ObservableList<Playlist> listaPlaylistsSeguidas;
    ObservableList<Playlist> listaTodasPlaylists;
    ObservableList<Playlist> listaPlEliminadas;
    ObservableList<Cancion> listaCancionesPlaylistUnica;
    ObservableList<Playlist> listaPlaylistsPatrocinadas;
    ObservableList<Podcast> listaTodosPodcasts;
    ObservableList<Podcast> listaPodcastsSeguidos;
    ObservableList<Capitulo> listaCapitulosPodcastUnico;

    // botones cambio de vistas
    @FXML
    public HBox botonHome;
    @FXML
    public Button botonAlbum;
    @FXML
    public Button botonArtistas;
    @FXML
    public Button botonPodcasts;
    @FXML
    public Label botonPlaylist;
    @FXML
    public Button buttPremium;
    @FXML
    public Button botonRecentlyPlayed;
    @FXML
    public Button botonYourPl;
    @FXML
    public Button botonVerPlaylistUnicaCreada;
    @FXML
    public Button botonVerPlaylistUnicaSeguida;
    @FXML
    public Button botonVerPlaylistUnicaGeneral;
    @FXML
    public HBox botonCrearNuevaplaylist;
    @FXML
    public Button botonPlPatrocinadas;
    @FXML
    public Button verPlPatrocined;

    //variables utiles
    private LoginController controller2;
    private Stage stage;
    @FXML
    public Label usuarioActivo;
    String usuario;
    String tituloCancion;
    String tituloPlaylist;
    String nombreArtista;
    LocalDate todaysDate = LocalDate.now();
    LocalDateTime todaysDateHour = LocalDateTime.now();
    int idCancion;
    int idPlaylist;
    int idArtista;
    int contador = 0;
    public static Usuario user;
    @FXML
    public Label labelAlbumUnico;
    @FXML
    public ImageView convertirCancionFav;
    @FXML
    public Label tituloCancionInferior;
    @FXML
    public VBox botonAnyadirCancionAPlaylist;
    @FXML
    public Button nombreArtistaInferior;
    @FXML
    public ImageView gifMusicaActiva;
    @FXML
    public ImageView botonPlay;
    @FXML
    public ImageView botonPausa;
    @FXML
    public Label nomArtistaUnico;
    @FXML
    public ImageView imageArtistaUnico;
    @FXML
    public Button botonDejarSeguirArtista;
    @FXML
    public Button botonSeguirArtista;
    @FXML
    public Button botonEliminarPlCreadas;
    @FXML
    public Button botonDejarSeguirPl;
    @FXML
    public Label nombrePlaylistUnica;
    @FXML
    public TextField tituloNuevaPl;
    @FXML
    public Button botonCrearNuevaPl;
    @FXML
    public Button botonSeguirPlaylist;
    @FXML
    public Label labelTituloPodcast;
    @FXML
    public Button botonUnfollowPodcast;
    @FXML
    public Button botonFollowPodcast;
    @FXML
    public static Pane paneEresPremium;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hBoxCancionInferior.setVisible(false);
        botonPlay.setVisible(false);
        botonPausa.setVisible(true);
        //Tabla Albums
        listaAlbum  = FXCollections.observableArrayList();
        tituloAlbum.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        anyoAlbum.setCellValueFactory(new PropertyValueFactory<>("anyo"));
        //Tabla Albums
        listaCancionesAlbumUnico = FXCollections.observableArrayList();
        columnaCancionesAlbumUnico.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        //Tabla Artistas Seguidos
        listaArtistasSeguidos = FXCollections.observableArrayList();
        nombreTablaArtistasSeguidos.setCellValueFactory(new PropertyValueFactory<>("name"));
        imagenTablaArtistasSeguidos.setCellValueFactory(new PropertyValueFactory<>("img"));
        //Tabla Canciones Favoritas
        listaCancionesFav = FXCollections.observableArrayList();
        tituloTablaCancionesFav.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        //Tabla Lista Artistas Completa
        listaArtistasCompleta = FXCollections.observableArrayList();
        nombreTablaArtistasCompleta.setCellValueFactory(new PropertyValueFactory<>("name"));
        imagenTablaArtistasCompleta.setCellValueFactory(new PropertyValueFactory<>("img"));
        //Tabla Canciones Artista
        listaCancionesArtista = FXCollections.observableArrayList();
        tituloCancionesArtista.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        //Tabla albums Artista
        listaAlbumArtista = FXCollections.observableArrayList();
        tituloAlbumArtista.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        anyoAlbumArtista.setCellValueFactory(new PropertyValueFactory<>("anyo"));
        // Tabla de artista propietario
        listaArtistaPropietarioAlbum = FXCollections.observableArrayList();
        columnaNombreArtistaPropietarioAlbum.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnaImgArtistaPropietarioAlbum.setCellValueFactory(new PropertyValueFactory<>("img"));
        //Tabla recently played
        listaRecentlyPlayed = FXCollections.observableArrayList();
        cancionesRecentlyPlayed.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        //Tabla playlists creadas
        listaPlaylistsCreadas = FXCollections.observableArrayList();
        nombrePlCreada.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        //Tabla playlists seguidas
        listaPlaylistsSeguidas = FXCollections.observableArrayList();
        nombrePlSeguida.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        //Tabla todas playlists
        listaTodasPlaylists = FXCollections.observableArrayList();
        titutloTodasPl.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        //Tabla playlists eliminadas
        listaPlEliminadas = FXCollections.observableArrayList();
        tituloPlEliminadas.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        //Tabla canciones playlist Unica
        listaCancionesPlaylistUnica = FXCollections.observableArrayList();
        cancionesPlaylistUnica.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        //Tabla playlists patrocinadas
        listaPlaylistsPatrocinadas = FXCollections.observableArrayList();
        tituloPlPatrocinadas.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        //Tabla todos los podcasts
        listaTodosPodcasts = FXCollections.observableArrayList();
        tituloTodosPodcasts.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        anyoPodcastTodos.setCellValueFactory(new PropertyValueFactory<>("anyo"));
        //Tabla podcasts seguidos
        listaPodcastsSeguidos = FXCollections.observableArrayList();
        tituloPodcastsSeguidos.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        anyoPodcastSeguido.setCellValueFactory(new PropertyValueFactory<>("anyo"));
        //Otra tabla
        listaCapitulosPodcastUnico = FXCollections.observableArrayList();
        capitulosPodcastUnico.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        duracionPodcastUnico.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        //Cargar la lista de artistas
        try {
            cargarArtistas();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Cargar la lista de artistas que sigues
        try {
            cargarArtistasSeguidos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Cargar lista de canciones fav
        try {
            getCancionesFav();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Métodos basicos

    public void onButtPremiumClick(MouseEvent mouseEvent) throws IOException {
        SpotifyApplication.premiumView();
    }

    public void nombreUsuario(String text, Stage stage, LoginController loginController) {
        usuarioActivo.setText(text.toUpperCase(Locale.ROOT));
        usuario = text;
        this.controller2 = loginController;
        this.stage = stage;
    }


    public void onHomeButtClick(MouseEvent mouseEvent) {
        cargarVistaPrincipal();
    }

    private void cargarVistaPrincipal() {
        mainView.setVisible(true);
        buscarArtistaUnico.setVisible(false);
        vistaPlaylistUnica.setVisible(false);
        vistaAlbumsGeneral.setVisible(false);
        vistaArtistasGeneral.setVisible(false);
        vistaAlbumUnico.setVisible(false);
        vistaRecentlyPlayed.setVisible(false);
        vistaYourPl.setVisible(false);
        vistaPlaylistsGeneral.setVisible(false);
        vistaCrearPlaylist.setVisible(false);
        vistaPlaylistsPatrocinadas.setVisible(false);
        vistaPodcasts.setVisible(false);
        vistaPodcastUnico.setVisible(false);
    }

    // CONSULTA PARA SABER A QUE ARTISTA PERTENECE UNA CANCION
    public void cancionPerteneceArtista() throws SQLException {
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT nombre FROM artista" +
                " INNER JOIN album ON artista.id = album.artista_id" +
                " INNER JOIN cancion ON cancion.album_id = album.id" +
                " WHERE cancion.titulo LIKE '"+tituloCancion+"'");
        if(resultSet.next()){
            String nombreArtista = resultSet.getString("nombre");
            nombreArtistaInferior.setText(nombreArtista);
        }
    }


    //PANE PRINCIPAL----------------------------------------
    //ARTISTAS SEGUIDOS
    public void cargarArtistasSeguidos() throws SQLException {
        listaArtistasSeguidos.clear();
        Connection con = conexion();
        Statement st = con.createStatement();
        List<Integer> followedIds = new ArrayList<>(consultaArtista2(con));
        for (int id : followedIds){
            ResultSet query = st.executeQuery("select * from artista where id = "+id+";");
            while (query.next()){
                int id1 = query.getInt("id");
                String name = query.getString("nombre");
                String ruta = query.getString("imagen");
                Image img = new Image(getClass().getResourceAsStream(ruta));
                ImageView imageView = new ImageView(img);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                Artista artista = new Artista(id1,name,imageView,ruta);
                listaArtistasSeguidos.add(artista);
            }
        }
        tablaArtistasSeguidos.setItems(listaArtistasSeguidos);
    }
    public void cargarArtistas() throws SQLException {
        artistList = new ArrayList<>();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT * FROM artista");
        while (resultSet.next()){
            int id1 = resultSet.getInt("id");
            String name = resultSet.getString("nombre");
            String ruta = resultSet.getString("imagen");
            Image img = new Image(getClass().getResourceAsStream(ruta));
            ImageView imageView = new ImageView(img);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            Artista artista = new Artista(id1,name,imageView, ruta);
            artistList.add(artista);
        }
    }

    public List<Integer> consultaArtista2(Connection con) throws SQLException {
        Statement s = null;
        ResultSet rs = null;
        List<Integer> followedIds = new ArrayList<>();
        for(Artista artista : artistList){
            try {
                s = con.createStatement();
                rs = s.executeQuery ("SELECT artista_id FROM sigue_artista WHERE usuario_id ='"+user.getId()+"' and artista_id='"+artista.getId()+"'");
                while (rs.next()){
                    followedIds.add(rs.getInt(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return followedIds;
    }

    //CANCIONES FAVORITAS

    public void convertirCancionFav(MouseEvent mouseEvent) throws SQLException {
        tituloCancion = tituloCancionInferior.getText();
        Connection con = conexion();
        Statement s = con.createStatement();
        Statement st = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM cancion" +
                " INNER JOIN guarda_cancion ON cancion.id = guarda_cancion.cancion_id" +
                " INNER JOIN usuario ON usuario.id = guarda_cancion.usuario_id" +
                " WHERE cancion.id = (SELECT id from cancion WHERE titulo LIKE '"+ tituloCancion+"')AND usuario.id = '"+user.getId()+"'");

        if(rs.next()){
            Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/mg.png")));
            convertirCancionFav.setImage(img);
            //delete
            s.executeUpdate("DELETE from guarda_cancion WHERE cancion_id =(SELECT id FROM cancion WHERE titulo LIKE '"+tituloCancion+"')");
        }else{
            Image img2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/fav.png")));
            convertirCancionFav.setImage(img2);
            //insert
            s.executeUpdate("INSERT into guarda_cancion VALUES ("+user.getId()+","+idCancion+")");
        }
        getCancionesFav();

    }

    public void getCancionesFav() throws SQLException {
        listaCancionesFav.clear();
        Connection con = conexion();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM cancion" +
                " INNER JOIN guarda_cancion ON cancion.id = guarda_cancion.cancion_id" +
                " INNER JOIN usuario ON usuario.id = guarda_cancion.usuario_id" +
                " WHERE usuario_id = "+user.getId()+" ");
        while (rs.next()){
            int id = rs.getInt(1);
            String titulo = rs.getString("titulo");
            Cancion cancionFav = new Cancion(id,titulo);
            listaCancionesFav.add(cancionFav);
        }
        tablaCancionesFav.setItems(listaCancionesFav);
    }

    public void consultaCancionesFav(MouseEvent mouseEvent) throws SQLException {
        hBoxCancionInferior.setVisible(true);
        gifMusicaActiva.setVisible(true);
        botonPlay.setVisible(false);
        botonPausa.setVisible(true);
        if(mainView.isVisible()){
            tituloCancion = tablaCancionesFav.getSelectionModel().getSelectedItem().getTitulo();
            idCancion = tablaCancionesFav.getSelectionModel().getSelectedItem().getId();
            Cancion cancion = new Cancion(idCancion, tituloCancion);
            listaRecentlyPlayed.add(cancion);
            tablaRecentlyPlayed.setItems(listaRecentlyPlayed);
        }else if (vistaAlbumUnico.isVisible()){
            tituloCancion = tablaCancionesAlbumUnico.getSelectionModel().getSelectedItem().getTitulo();
            idCancion = tablaCancionesAlbumUnico.getSelectionModel().getSelectedItem().getId();
            Cancion cancion2 = new Cancion(idCancion, tituloCancion);
            listaRecentlyPlayed.add(cancion2);
            tablaRecentlyPlayed.setItems(listaRecentlyPlayed);
        }else if (buscarArtistaUnico.isVisible()){
            tituloCancion = cancionesArtista.getSelectionModel().getSelectedItem().getTitulo();
            idCancion = cancionesArtista.getSelectionModel().getSelectedItem().getId();
            Cancion cancion3 = new Cancion(idCancion, tituloCancion);
            listaRecentlyPlayed.add(cancion3);
            tablaRecentlyPlayed.setItems(listaRecentlyPlayed);
        }else if(vistaRecentlyPlayed.isVisible()){
            tituloCancion = tablaRecentlyPlayed.getSelectionModel().getSelectedItem().getTitulo();
            idCancion = tablaRecentlyPlayed.getSelectionModel().getSelectedItem().getId();
        }else if(vistaPlaylistUnica.isVisible()){
            tituloCancion = tablaCancionesPlaylistUnica.getSelectionModel().getSelectedItem().getTitulo();
            idCancion = tablaCancionesPlaylistUnica.getSelectionModel().getSelectedItem().getId();
            Cancion cancion4 = new Cancion(idCancion, tituloCancion);
            listaRecentlyPlayed.add(cancion4);
            tablaRecentlyPlayed.setItems(listaRecentlyPlayed);
        }

        Connection con = conexion();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM cancion" +
                " INNER JOIN guarda_cancion ON cancion.id = guarda_cancion.cancion_id" +
                " INNER JOIN usuario ON usuario.id = guarda_cancion.usuario_id" +
                " WHERE cancion.id = (SELECT id from cancion WHERE titulo LIKE '"+ tituloCancion+"')AND usuario.id = "+user.getId()+" ");

        if(rs.next()){
            Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/fav.png")));
            convertirCancionFav.setImage(img);
        }else{
            Image img2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/mg.png")));
            convertirCancionFav.setImage(img2);
        }
        tituloCancionInferior.setText(tituloCancion);

        cancionPerteneceArtista();
    }

    // PANE ALBUMS PRINCIPAL

    public void cargarAlbum() throws SQLException {
        listaAlbum.clear();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet query = st.executeQuery("select * FROM album");
        while (query.next()){
            String id = query.getString("id");
            String titulo = query.getString("titulo");
            int anyo = query.getInt("anyo");
            Album album = new Album(id, titulo, anyo);
            listaAlbum.add(album);
        }
        tablaAlbums.setItems(listaAlbum);
    }

    public void onAlbumButtonClick(MouseEvent mouseEvent) {
        mainView.setVisible(false);
        buscarArtistaUnico.setVisible(false);
        vistaPlaylistUnica.setVisible(false);
        vistaAlbumsGeneral.setVisible(true);
        vistaArtistasGeneral.setVisible(false);
        vistaAlbumUnico.setVisible(false);
        vistaRecentlyPlayed.setVisible(false);
        vistaYourPl.setVisible(false);
        vistaPlaylistsGeneral.setVisible(false);
        vistaCrearPlaylist.setVisible(false);
        vistaPlaylistsPatrocinadas.setVisible(false);
        vistaPodcasts.setVisible(false);
        vistaPodcastUnico.setVisible(false);
        try {
            cargarAlbum();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // PANE ALBUM ÚNICO

    public void cargarAlbumUnico(){
        mainView.setVisible(false);
        buscarArtistaUnico.setVisible(false);
        vistaPlaylistUnica.setVisible(false);
        vistaAlbumsGeneral.setVisible(false);
        vistaArtistasGeneral.setVisible(false);
        vistaAlbumUnico.setVisible(true);
        vistaRecentlyPlayed.setVisible(false);
        vistaYourPl.setVisible(false);
        vistaPlaylistsGeneral.setVisible(false);
        vistaCrearPlaylist.setVisible(false);
        vistaPlaylistsPatrocinadas.setVisible(false);
        vistaPodcasts.setVisible(false);
        vistaPodcastUnico.setVisible(false);
    }
    public void albumUnicoClick(MouseEvent mouseEvent) throws SQLException {
        cargarAlbumUnico();
        String tituloAlbum = tablaAlbums.getSelectionModel().getSelectedItem().getTitulo();
        labelAlbumUnico.setText(tituloAlbum);
        cargarCancionesAlbum();
        consultaCargarArtistaAlbum(tituloAlbum);
    }
    public void cargarCancionesAlbum() throws SQLException {
        listaCancionesAlbumUnico.clear();
        String idAlbum = tablaAlbums.getSelectionModel().getSelectedItem().getId();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery("select * FROM cancion WHERE album_id = '"+idAlbum+"'");
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String titulo = resultSet.getString("titulo");
            Cancion cancion = new Cancion(id,titulo);
            listaCancionesAlbumUnico.add(cancion);
        }
        tablaCancionesAlbumUnico.setItems(listaCancionesAlbumUnico);
    }

    private void consultaCargarArtistaAlbum(String tituloAlbum) throws SQLException {
        listaArtistaPropietarioAlbum.clear();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery("select * FROM artista" +
                " INNER JOIN album ON artista.id = album.artista_id AND album.titulo = '"+tituloAlbum+"'");
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("nombre");
            String ruta = resultSet.getString("imagen");
            Image img = new Image(getClass().getResourceAsStream(ruta));
            ImageView imageView = new ImageView(img);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            Artista artista = new Artista(id,name,imageView, ruta);
            listaArtistaPropietarioAlbum.add(artista);
        }
        tablaArtistaPropietarioAlbum.setItems(listaArtistaPropietarioAlbum);
    }

    public void albumUnicoClick2(MouseEvent mouseEvent) throws SQLException {
        cargarAlbumUnico();
        String tituloAlbum = albumArtista.getSelectionModel().getSelectedItem().getTitulo();
        labelAlbumUnico.setText(tituloAlbum);
        cargarCancionesAlbum2();
        consultaCargarArtistaAlbum2(tituloAlbum);
    }

    private void consultaCargarArtistaAlbum2(String tituloAlbum) throws SQLException {
        listaArtistaPropietarioAlbum.clear();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery("select * FROM artista" +
                " INNER JOIN album ON artista.id = album.artista_id AND album.titulo = '"+tituloAlbum+"'");
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("nombre");
            String ruta = resultSet.getString("imagen");
            Image img = new Image(getClass().getResourceAsStream(ruta));
            ImageView imageView = new ImageView(img);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            Artista artista = new Artista(id,name,imageView, ruta);
            listaArtistaPropietarioAlbum.add(artista);
        }
        tablaArtistaPropietarioAlbum.setItems(listaArtistaPropietarioAlbum);
    }


    public void cargarCancionesAlbum2() throws SQLException {
        listaCancionesAlbumUnico.clear();
        String idAlbum = albumArtista.getSelectionModel().getSelectedItem().getId();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery("select * FROM cancion WHERE album_id = '"+idAlbum+"'");
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String titulo = resultSet.getString("titulo");
            Cancion cancion = new Cancion(id,titulo);
            listaCancionesAlbumUnico.add(cancion);
        }
        tablaCancionesAlbumUnico.setItems(listaCancionesAlbumUnico);
    }

    // PANE ARTISTAS COMPLETO


    public void onArtistasButtonClick(MouseEvent mouseEvent) throws SQLException {
        mainView.setVisible(false);
        buscarArtistaUnico.setVisible(false);
        vistaPlaylistUnica.setVisible(false);
        vistaAlbumsGeneral.setVisible(false);
        vistaArtistasGeneral.setVisible(true);
        vistaAlbumUnico.setVisible(false);
        vistaRecentlyPlayed.setVisible(false);
        vistaYourPl.setVisible(false);
        vistaPlaylistsGeneral.setVisible(false);
        vistaCrearPlaylist.setVisible(false);
        vistaPlaylistsPatrocinadas.setVisible(false);
        vistaPodcasts.setVisible(false);
        vistaPodcastUnico.setVisible(false);
        cargarArtistasGeneral();
    }

    public void cargarArtistasGeneral() throws SQLException {
        listaArtistasCompleta.clear();
        listaArtistasCompleta.addAll(artistList);
        tablaArtistasCompleta.setItems(listaArtistasCompleta);
    }

    // PANE ARTISTA UNICO

    public void cargarVistaArtistaUnico(){
        mainView.setVisible(false);
        buscarArtistaUnico.setVisible(true);
        vistaPlaylistUnica.setVisible(false);
        vistaAlbumsGeneral.setVisible(false);
        vistaArtistasGeneral.setVisible(false);
        vistaAlbumUnico.setVisible(false);
        vistaRecentlyPlayed.setVisible(false);
        vistaYourPl.setVisible(false);
        vistaPlaylistsGeneral.setVisible(false);
        vistaCrearPlaylist.setVisible(false);
        vistaPlaylistsPatrocinadas.setVisible(false);
        vistaPodcasts.setVisible(false);
        vistaPodcastUnico.setVisible(false);
    }

    public void getArtistaUnico(MouseEvent mouseEvent) throws SQLException {
        cargarVistaArtistaUnico();
        String nomArtista = tablaArtistasCompleta.getSelectionModel().getSelectedItem().getName();
        nomArtistaUnico.setText(nomArtista);
        String ruta = tablaArtistasCompleta.getSelectionModel().getSelectedItem().getRuta();
        Image imgArtista = new Image(getClass().getResourceAsStream(ruta));
        imageArtistaUnico.setImage(imgArtista);
        encontrarCancionesArtistaUnico();
        int idArtista = tablaArtistasCompleta.getSelectionModel().getSelectedItem().getId();
        cargarAlbumArtista(idArtista);

    }

    public void getArtistaUnico2(MouseEvent mouseEvent) throws SQLException {
        cargarVistaArtistaUnico();
        String nomArtista1 = tablaArtistasSeguidos.getSelectionModel().getSelectedItem().getName();
        nomArtistaUnico.setText(nomArtista1);
        String ruta = tablaArtistasSeguidos.getSelectionModel().getSelectedItem().getRuta();
        Image imgArtista1 = new Image(getClass().getResourceAsStream(ruta));
        imageArtistaUnico.setImage(imgArtista1);
        encontrarCancionesArtistaUnico2();
        int idArtista = tablaArtistasSeguidos.getSelectionModel().getSelectedItem().getId();
        cargarAlbumArtista(idArtista);

    }
    public void getArtistaUnico3(MouseEvent mouseEvent) throws SQLException {
        cargarVistaArtistaUnico();
        String nomArtista2 = tablaArtistaPropietarioAlbum.getSelectionModel().getSelectedItem().getName();
        nomArtistaUnico.setText(nomArtista2);
        String ruta = tablaArtistaPropietarioAlbum.getSelectionModel().getSelectedItem().getRuta();
        Image imgArtista1 = new Image(getClass().getResourceAsStream(ruta));
        imageArtistaUnico.setImage(imgArtista1);
        encontrarCancionesArtistaUnico3();
        int idArtista = tablaArtistaPropietarioAlbum.getSelectionModel().getSelectedItem().getId();
        cargarAlbumArtista(idArtista);

    }

    public void encontrarCancionesArtistaUnico() throws SQLException {
        listaCancionesArtista.clear();
        int id1 = tablaArtistasCompleta.getSelectionModel().getSelectedItem().getId();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT * FROM cancion" +
                " INNER JOIN album ON album.id = cancion.album_id" +
                " INNER JOIN artista ON album.artista_id = artista.id" +
                " WHERE artista.id = "+id1);
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String titulo = resultSet.getString("titulo");
            Cancion cancion = new Cancion(id,titulo);
            listaCancionesArtista.add(cancion);
        }
        cancionesArtista.setItems(listaCancionesArtista);
    }

    public void encontrarCancionesArtistaUnico2() throws SQLException {
        listaCancionesArtista.clear();
        int id1 = tablaArtistasSeguidos.getSelectionModel().getSelectedItem().getId();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT * FROM cancion" +
                " INNER JOIN album ON album.id = cancion.album_id" +
                " INNER JOIN artista ON album.artista_id = artista.id" +
                " WHERE artista.id = "+id1+"");
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String titulo = resultSet.getString("titulo");
            Cancion cancion = new Cancion(id,titulo);
            listaCancionesArtista.add(cancion);
        }
        cancionesArtista.setItems(listaCancionesArtista);
    }

    public void encontrarCancionesArtistaUnico3() throws SQLException {
        listaCancionesArtista.clear();
        int id1 = tablaArtistaPropietarioAlbum.getSelectionModel().getSelectedItem().getId();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT * FROM cancion" +
                " INNER JOIN album ON album.id = cancion.album_id" +
                " INNER JOIN artista ON album.artista_id = artista.id" +
                " WHERE artista.id = "+id1+"");
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String titulo = resultSet.getString("titulo");
            Cancion cancion = new Cancion(id,titulo);
            listaCancionesArtista.add(cancion);
        }
        cancionesArtista.setItems(listaCancionesArtista);
    }

    public void cargarAlbumArtista(int idArtista) throws SQLException {
        listaAlbumArtista.clear();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet query = st.executeQuery("select * FROM album WHERE artista_id = "+idArtista+"");
        while (query.next()){
            String id = query.getString("id");
            String titulo = query.getString("titulo");
            int anyo = query.getInt("anyo");
            Album album = new Album(id, titulo, anyo);
            listaAlbumArtista.add(album);
        }
        albumArtista.setItems(listaAlbumArtista);
    }

    // PANE PLAYLISTS

    public void onButtPlaylistClick(MouseEvent mouseEvent) throws SQLException {
        cargarVistaPlaylistGeneral();
        cargarTablaTodasPl();
        cargarTablaPlEliminadas();
    }

    private void cargarTablaTodasPl() throws SQLException {
        listaTodasPlaylists.clear();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet query = st.executeQuery("SELECT * FROM playlist");
        while (query.next()){
            int id = query.getInt("id");
            String titulo = query.getString("titulo");
            Playlist playlist = new Playlist(id,titulo);
            listaTodasPlaylists.add(playlist);
        }
        tablaTodasPlaylists.setItems(listaTodasPlaylists);
    }

    private void cargarTablaPlEliminadas() throws SQLException {
        listaPlEliminadas.clear();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet query = st.executeQuery("SELECT * FROM playlist" +
                " INNER JOIN eliminada ON playlist.id = eliminada.playlist_id");
        while (query.next()){
            int id = query.getInt("id");
            String titulo = query.getString("titulo");
            Playlist playlist = new Playlist(id,titulo);
            listaPlEliminadas.add(playlist);
        }
        tablaPlEliminadas.setItems(listaPlEliminadas);
    }

    public void cargarVistaPlaylistGeneral(){
        mainView.setVisible(false);
        buscarArtistaUnico.setVisible(false);
        vistaPlaylistUnica.setVisible(false);
        vistaAlbumsGeneral.setVisible(false);
        vistaArtistasGeneral.setVisible(false);
        vistaAlbumUnico.setVisible(false);
        vistaRecentlyPlayed.setVisible(false);
        vistaYourPl.setVisible(false);
        vistaPlaylistsGeneral.setVisible(true);
        vistaCrearPlaylist.setVisible(false);
        vistaPlaylistsPatrocinadas.setVisible(false);
        vistaPodcasts.setVisible(false);
        vistaPodcastUnico.setVisible(false);
    }

    // PANE YOUR PLAYLISTS
    public void cargarVistaYourPl(MouseEvent mouseEvent) throws SQLException {
        cargarVistaYourPlaylists();
        cargarTablaPlCreadas();
        cargarTablaPlSeguidas();

    }

    private void cargarVistaYourPlaylists() {
        mainView.setVisible(false);
        buscarArtistaUnico.setVisible(false);
        vistaPlaylistUnica.setVisible(false);
        vistaAlbumsGeneral.setVisible(false);
        vistaArtistasGeneral.setVisible(false);
        vistaAlbumUnico.setVisible(false);
        vistaRecentlyPlayed.setVisible(false);
        vistaYourPl.setVisible(true);
        vistaPlaylistsGeneral.setVisible(false);
        vistaCrearPlaylist.setVisible(false);
        vistaPlaylistsPatrocinadas.setVisible(false);
        vistaPodcasts.setVisible(false);
        vistaPodcastUnico.setVisible(false);
    }

    private void cargarTablaPlCreadas() throws SQLException {
        listaPlaylistsCreadas.clear();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet query = st.executeQuery("SELECT * FROM playlist" +
                " WHERE usuario_id = "+user.getId()+"");
        while (query.next()){
            int id = query.getInt("id");
            String titulo = query.getString("titulo");
            Playlist playlist = new Playlist(id,titulo);
            listaPlaylistsCreadas.add(playlist);
        }
        tablaPlaylistsCreadas.setItems(listaPlaylistsCreadas);
    }

    private void cargarTablaPlSeguidas() throws SQLException {
        listaPlaylistsSeguidas.clear();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet query = st.executeQuery("SELECT * FROM playlist" +
                " INNER JOIN sigue_playlist ON playlist.id = sigue_playlist.playlist_id AND sigue_playlist.usuario_id = "+user.getId()+"");
        while (query.next()){
            int id = query.getInt("id");
            String titulo = query.getString("titulo");
            Playlist playlist = new Playlist(id,titulo);
            listaPlaylistsSeguidas.add(playlist);
        }
        tablaPlaylistsSeguidas.setItems(listaPlaylistsSeguidas);
    }

    public void eliminarPlCreada(MouseEvent mouseEvent) throws SQLException {
        tituloPlaylist = tablaPlaylistsCreadas.getSelectionModel().getSelectedItem().getTitulo();
        idPlaylist = tablaPlaylistsCreadas.getSelectionModel().getSelectedItem().getId();
        Connection con = conexion();
        Statement st = con.createStatement();
        Statement s = con.createStatement();
        ResultSet query = st.executeQuery("SELECT * FROM playlist" +
                " WHERE usuario_id = "+user.getId()+ " AND titulo = '"+tituloPlaylist+"'");
        if (query.next()){
            try{
                st.executeUpdate("INSERT INTO eliminada (fecha_eliminacion, playlist_id) VALUES ('"+todaysDate+"', "+idPlaylist+")");
                Alert alert;
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Bien!");
                alert.setContentText("Se ha eliminado "+tituloPlaylist+" de tu lista");
                alert.showAndWait();
            }catch (SQLIntegrityConstraintViolationException e){
                Alert alert;
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Esta Playlist ya no existe");
                alert.showAndWait();
            }
        }
        cargarTablaPlCreadas();
    }

    public void dejarSeguirPl(MouseEvent mouseEvent) throws SQLException {
        tituloPlaylist = tablaPlaylistsSeguidas.getSelectionModel().getSelectedItem().getTitulo();
        idPlaylist = tablaPlaylistsSeguidas.getSelectionModel().getSelectedItem().getId();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet query = st.executeQuery("SELECT * FROM playlist" +
                " INNER JOIN sigue_playlist ON playlist.id = sigue_playlist.playlist_id AND sigue_playlist.usuario_id = "+user.getId()+" AND sigue_playlist.playlist_id = "+idPlaylist+"");
        if (query.next()){
            st.executeUpdate("DELETE FROM sigue_playlist WHERE (usuario_id = '"+user.getId()+"') and (playlist_id = '"+idPlaylist+"') ");
            Alert alert;
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Bien!");
            alert.setContentText("Has dejado de seguir la "+tituloPlaylist);
            alert.showAndWait();
        }
        cargarTablaPlSeguidas();
    }

    // PANE PLAYLIST UNICA

    public void vistaPlaylistUnicaCreada(MouseEvent mouseEvent) throws SQLException {
        listaCancionesPlaylistUnica.clear();
        try{
            tituloPlaylist = tablaPlaylistsCreadas.getSelectionModel().getSelectedItem().getTitulo();
            idPlaylist = tablaPlaylistsCreadas.getSelectionModel().getSelectedItem().getId();
            nombrePlaylistUnica.setText(tituloPlaylist);
            cargarVistaPlaylistUnica();
        }catch(RuntimeException e){
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Selecciona una playlist de la lista Creadas");
            alert.showAndWait();
        }
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT * FROM cancion" +
                " INNER JOIN anyade_cancion_playlist ON cancion.id = anyade_cancion_playlist.cancion_id" +
                " INNER JOIN playlist ON playlist.id = anyade_cancion_playlist.playlist_id" +
                " INNER JOIN usuario on usuario.id = anyade_cancion_playlist.usuario_id" +
                " WHERE playlist.id ="+idPlaylist+" AND usuario.id = "+user.getId()+"");
        while(resultSet.next()){
            int idCancion = resultSet.getInt("id");
            String titCancion = resultSet.getString("titulo");
            Cancion cancion = new Cancion(idCancion, titCancion);
            listaCancionesPlaylistUnica.add(cancion);
        }
        tablaCancionesPlaylistUnica.setItems(listaCancionesPlaylistUnica);

    }
    public void vistaPlaylistUnicaSeguida(MouseEvent mouseEvent) throws SQLException {
        listaCancionesPlaylistUnica.clear();
        try{
            tituloPlaylist = tablaPlaylistsSeguidas.getSelectionModel().getSelectedItem().getTitulo();
            idPlaylist = tablaPlaylistsSeguidas.getSelectionModel().getSelectedItem().getId();
            nombrePlaylistUnica.setText(tituloPlaylist);
            cargarVistaPlaylistUnica();
        }catch(RuntimeException e){
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Selecciona una playlist de la lista Seguidas");
            alert.showAndWait();
        }
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT * FROM cancion" +
                " INNER JOIN anyade_cancion_playlist ON cancion.id = anyade_cancion_playlist.cancion_id" +
                " INNER JOIN playlist ON playlist.id = anyade_cancion_playlist.playlist_id" +
                " INNER JOIN usuario on usuario.id = anyade_cancion_playlist.usuario_id" +
                " WHERE playlist.id ="+idPlaylist+" AND usuario.id = "+user.getId()+"");
        while(resultSet.next()){
            int idCancion = resultSet.getInt("id");
            String titCancion = resultSet.getString("titulo");
            Cancion cancion = new Cancion(idCancion, titCancion);
            listaCancionesPlaylistUnica.add(cancion);
        }
        tablaCancionesPlaylistUnica.setItems(listaCancionesPlaylistUnica);

    }

    public void vistaPlaylistUnicaGeneral(MouseEvent mouseEvent) throws SQLException {
        listaCancionesPlaylistUnica.clear();
        try{
            tituloPlaylist = tablaTodasPlaylists.getSelectionModel().getSelectedItem().getTitulo();
            idPlaylist = tablaTodasPlaylists.getSelectionModel().getSelectedItem().getId();
            nombrePlaylistUnica.setText(tituloPlaylist);
            cargarVistaPlaylistUnica();
        }catch(RuntimeException e){
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Selecciona una playlist");
            alert.showAndWait();
        }
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT * FROM cancion" +
                " INNER JOIN anyade_cancion_playlist ON cancion.id = anyade_cancion_playlist.cancion_id" +
                " INNER JOIN playlist ON playlist.id = anyade_cancion_playlist.playlist_id" +
                " INNER JOIN usuario on usuario.id = anyade_cancion_playlist.usuario_id" +
                " WHERE playlist.id ="+idPlaylist+" AND usuario.id = "+user.getId()+"");
        while(resultSet.next()){
            int idCancion = resultSet.getInt("id");
            String titCancion = resultSet.getString("titulo");
            Cancion cancion = new Cancion(idCancion, titCancion);
            listaCancionesPlaylistUnica.add(cancion);
        }
        tablaCancionesPlaylistUnica.setItems(listaCancionesPlaylistUnica);
    }

    public void seguirPl(MouseEvent mouseEvent) throws SQLException {
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet query = st.executeQuery("SELECT * FROM playlist" +
                " INNER JOIN sigue_playlist ON playlist.id = sigue_playlist.playlist_id AND sigue_playlist.usuario_id = "+user.getId()+" AND sigue_playlist.playlist_id = "+idPlaylist+"");
        if (query.next()){
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Ya sigues la Playlist "+ tituloPlaylist);
            alert.showAndWait();
        }else{
            st.executeUpdate("INSERT into sigue_playlist VALUES ('"+user.getId()+"',(SELECT id from playlist WHERE titulo LIKE '"+tituloPlaylist+"'))");
            Alert alert;
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Bien!");
            alert.setContentText("Has comenzado a seguir la Playlist "+tituloPlaylist);
            alert.showAndWait();
        }

    }

    private void cargarVistaPlaylistUnica() {
        mainView.setVisible(false);
        buscarArtistaUnico.setVisible(false);
        vistaPlaylistUnica.setVisible(true);
        vistaAlbumsGeneral.setVisible(false);
        vistaArtistasGeneral.setVisible(false);
        vistaAlbumUnico.setVisible(false);
        vistaRecentlyPlayed.setVisible(false);
        vistaYourPl.setVisible(false);
        vistaPlaylistsGeneral.setVisible(false);
        vistaCrearPlaylist.setVisible(false);
        vistaPlaylistsPatrocinadas.setVisible(false);
        vistaPodcasts.setVisible(false);
        vistaPodcastUnico.setVisible(false);
    }

    // AÑADIR CANCIONES A PLAYLISTS

    public void anyadirCancionAPl(MouseEvent mouseEvent) throws SQLException {
        if(vistaYourPl.isVisible()){
            try{
                idPlaylist = tablaPlaylistsCreadas.getSelectionModel().getSelectedItem().getId();
                tituloPlaylist = tablaPlaylistsCreadas.getSelectionModel().getSelectedItem().getTitulo();
                Alert alert;
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Perfecto!");
                alert.setContentText("Se ha añadido correctamente a "+tituloPlaylist);
                alert.showAndWait();

            }catch(RuntimeException e){
                idPlaylist = tablaPlaylistsSeguidas.getSelectionModel().getSelectedItem().getId();
                tituloPlaylist = tablaPlaylistsSeguidas.getSelectionModel().getSelectedItem().getTitulo();
                Alert alert;
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Perfecto!");
                alert.setContentText("Se ha añadido correctamente a "+tituloPlaylist);
                alert.showAndWait();
            }/*catch(SQLIntegrityConstraintViolationException e){
                Alert alert2;
                alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setHeaderText(null);
                alert2.setTitle("Perfecto!");
                alert2.setContentText("Esta cancion ya pertenece a "+tituloPlaylist);
                alert2.showAndWait();
                //No se porque pero no me deja poner 2 catch me dice de borrarlo y me da error
                //Por lo que no se como arreglar la excepcion que salta, pero por lo menos no inserta 2 canciones iguales
            }*/
        }else if(vistaPlaylistsGeneral.isVisible()){
            idPlaylist = tablaTodasPlaylists.getSelectionModel().getSelectedItem().getId();
            tituloPlaylist = tablaTodasPlaylists.getSelectionModel().getSelectedItem().getTitulo();
            Alert alert;
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Perfecto!");
            alert.setContentText("Se ha añadido correctamente a "+tituloPlaylist);
            alert.showAndWait();
        }
        Connection con = conexion();
        Statement st = con.createStatement();
        try{
            st.executeUpdate("INSERT INTO anyade_cancion_playlist (playlist_id, cancion_id, usuario_id, fecha_anyadida) VALUES ('"+idPlaylist+"', '"+idCancion+"', '"+user.getId()+"', '"+todaysDateHour+"')");
        }catch(SQLIntegrityConstraintViolationException e) {
            Alert alert2;
            alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setHeaderText(null);
            alert2.setTitle("Error");
            alert2.setContentText("Esta cancion ya pertenece a " + tituloPlaylist);
            alert2.showAndWait();
        }
    }

    // PODER SEGUIR ARTISTAS

    public void dejarSeguirArtista(MouseEvent mouseEvent) throws SQLException {
        String nomArt = nomArtistaUnico.getText();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet query = st.executeQuery("SELECT * FROM artista" +
                " INNER JOIN sigue_artista ON artista.id = sigue_artista.artista_id" +
                " INNER JOIN usuario ON usuario.id = sigue_artista.usuario_id" +
                " WHERE artista.id = (SELECT id from artista WHERE nombre LIKE '"+nomArt+"')AND usuario.id = "+user.getId()+"");

        if (query.next()){
            st.executeUpdate("DELETE from sigue_artista WHERE artista_id =(SELECT id FROM artista WHERE nombre = '"+nomArt+"')");
            Alert alert;
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Perfecto!");
            alert.setContentText("Has dejado de seguir a "+nomArt);
            alert.showAndWait();

        }else{
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No sigues a "+nomArt);
            alert.showAndWait();
        }
        cargarArtistasSeguidos();
    }

    public void seguirArtista(MouseEvent mouseEvent) throws SQLException {
        String nomArt = nomArtistaUnico.getText();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT * FROM artista" +
                " INNER JOIN sigue_artista ON artista.id = sigue_artista.artista_id" +
                " INNER JOIN usuario ON usuario.id = sigue_artista.usuario_id" +
                " WHERE artista.id = (SELECT id from artista WHERE nombre LIKE '"+nomArt+"')AND usuario.id = "+user.getId()+"");
        if (resultSet.next()){
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Ya sigues a "+nomArt);
            alert.showAndWait();
        }else{
            st.executeUpdate("INSERT into sigue_artista VALUES ('"+user.getId()+"',(SELECT id from artista WHERE nombre LIKE '"+nomArt+"'))");
            Alert alert;
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Perfecto!");
            alert.setContentText("Has comenzado a seguir a "+nomArt);
            alert.showAndWait();
        }
        cargarArtistasSeguidos();
    }



    // BOTONES INFERIORES

    public void playPauseButtonCLick(MouseEvent mouseEvent) {
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/play1.png")));
        Image img2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/pause.png")));
        if(gifMusicaActiva.isVisible()){
            gifMusicaActiva.setVisible(false);
        }else{
            gifMusicaActiva.setVisible(true);
        }

        if(botonPlay.isVisible()){
            botonPlay.setVisible(false);
            botonPausa.setVisible(true);
        }else{
            botonPlay.setVisible(true);
            botonPausa.setVisible(false);
        }

    }

    // PANE RECENTLY PLAYED

    public void onRecentlyPlayedClick(MouseEvent mouseEvent) {
        cargarvistaRecentlyPlayed();

    }

    private void cargarvistaRecentlyPlayed() {
        mainView.setVisible(false);
        buscarArtistaUnico.setVisible(false);
        vistaPlaylistUnica.setVisible(false);
        vistaAlbumsGeneral.setVisible(false);
        vistaArtistasGeneral.setVisible(false);
        vistaAlbumUnico.setVisible(false);
        vistaRecentlyPlayed.setVisible(true);
        vistaYourPl.setVisible(false);
        vistaPlaylistsGeneral.setVisible(false);
        vistaCrearPlaylist.setVisible(false);
        vistaPlaylistsPatrocinadas.setVisible(false);
        vistaPodcasts.setVisible(false);
        vistaPodcastUnico.setVisible(false);
    }

    // CREAR PLAYLISTS NUEVAS
    public void crearNuevaPlaylist(MouseEvent mouseEvent) {
        cargarVistaCrearPl();

    }


    public void insertarNuevaPl(MouseEvent mouseEvent) throws SQLException {
        if(tituloNuevaPl.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes ponerle título a la Playlist");
            alert.showAndWait();
        }else{
            Connection con = conexion();
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO playlist (titulo, numero_canciones, fecha_creacion, usuario_id) VALUES ('"+tituloNuevaPl.getText()+"', '0', '"+todaysDate+"', '"+user.getId()+"')");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Perfecto!");
            alert.setContentText("Se ha añadido "+tituloNuevaPl.getText()+" a tus Playlists");
            alert.showAndWait();
            cargarVistaPrincipal();
        }
    }

    private void cargarVistaCrearPl() {
        mainView.setVisible(false);
        buscarArtistaUnico.setVisible(false);
        vistaPlaylistUnica.setVisible(false);
        vistaAlbumsGeneral.setVisible(false);
        vistaArtistasGeneral.setVisible(false);
        vistaAlbumUnico.setVisible(false);
        vistaRecentlyPlayed.setVisible(false);
        vistaYourPl.setVisible(false);
        vistaPlaylistsGeneral.setVisible(false);
        vistaCrearPlaylist.setVisible(true);
        vistaPlaylistsPatrocinadas.setVisible(false);
        vistaPodcasts.setVisible(false);
        vistaPodcastUnico.setVisible(false);
    }

    // PANE PLAYLISTS PATROCINADAS
    public void cargarVistaPlaylistPatrocinadas(MouseEvent mouseEvent) throws SQLException {
        cargarVistaPlPatro();
        llenarTablaPatrocinadas();
    }

    private void llenarTablaPatrocinadas() throws SQLException {
        listaPlaylistsPatrocinadas.clear();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet query = st.executeQuery("SELECT * FROM playlist" +
                " INNER JOIN patrocinada ON playlist.id = patrocinada.playlist_id");
        while (query.next()){
            int id = query.getInt("id");
            String titulo = query.getString("titulo");
            Playlist playlist = new Playlist(id,titulo);
            listaPlaylistsPatrocinadas.add(playlist);
        }
        tablaPlPatrocinadas.setItems(listaPlaylistsPatrocinadas);
    }

    private void cargarVistaPlPatro() {
        mainView.setVisible(false);
        buscarArtistaUnico.setVisible(false);
        vistaPlaylistUnica.setVisible(false);
        vistaAlbumsGeneral.setVisible(false);
        vistaArtistasGeneral.setVisible(false);
        vistaAlbumUnico.setVisible(false);
        vistaRecentlyPlayed.setVisible(false);
        vistaYourPl.setVisible(false);
        vistaPlaylistsGeneral.setVisible(false);
        vistaCrearPlaylist.setVisible(false);
        vistaPlaylistsPatrocinadas.setVisible(true);
        vistaPodcasts.setVisible(false);
        vistaPodcastUnico.setVisible(false);
    }

    public void vistaPlaylistUnicaPatrocinada(MouseEvent mouseEvent) throws SQLException {
        listaCancionesPlaylistUnica.clear();
        try{
            tituloPlaylist = tablaPlPatrocinadas.getSelectionModel().getSelectedItem().getTitulo();
            idPlaylist = tablaPlPatrocinadas.getSelectionModel().getSelectedItem().getId();
            nombrePlaylistUnica.setText(tituloPlaylist);
            cargarVistaPlaylistUnica();
        }catch(RuntimeException e){
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Selecciona una playlist de la lista Patrocinadas");
            alert.showAndWait();
        }
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT * FROM cancion" +
                " INNER JOIN anyade_cancion_playlist ON cancion.id = anyade_cancion_playlist.cancion_id" +
                " INNER JOIN playlist ON playlist.id = anyade_cancion_playlist.playlist_id" +
                " INNER JOIN usuario on usuario.id = anyade_cancion_playlist.usuario_id" +
                " WHERE playlist.id ="+idPlaylist+" AND usuario.id = "+user.getId()+"");
        while(resultSet.next()){
            int idCancion = resultSet.getInt("id");
            String titCancion = resultSet.getString("titulo");
            Cancion cancion = new Cancion(idCancion, titCancion);
            listaCancionesPlaylistUnica.add(cancion);
        }
        tablaCancionesPlaylistUnica.setItems(listaCancionesPlaylistUnica);
    }

    // PANE PODCAST

    public void onPodcastButtonClick(MouseEvent mouseEvent) throws SQLException {

        cargarVistaPodcasts();
        cargarTablaTodosPodcasts();
        cargarTablaPodcastsSeguidos();
    }

    private void cargarTablaTodosPodcasts() throws SQLException {
        listaTodosPodcasts.clear();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet query = st.executeQuery("SELECT * FROM podcast");
        while (query.next()){
            int id2 = query.getInt("id");
            String titulo = query.getString("titulo");
            int anyo = query.getInt("anyo");
            Podcast podcast = new Podcast(id2,titulo,anyo);
            listaTodosPodcasts.add(podcast);
        }
        tablaTodosPodcasts.setItems(listaTodosPodcasts);
    }

    private void cargarTablaPodcastsSeguidos() throws SQLException {
        listaPodcastsSeguidos.clear();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet query = st.executeQuery("SELECT * FROM podcast" +
                " INNER JOIN podcast_usuario ON podcast.id = podcast_usuario.podcast_id" +
                " WHERE podcast_usuario.usuario_id = "+user.getId()+"");
        while (query.next()){
            int id3 = query.getInt("id");
            String titulo = query.getString("titulo");
            int anyo = query.getInt("anyo");
            Podcast podcast = new Podcast(id3,titulo,anyo);
            listaPodcastsSeguidos.add(podcast);
        }
        tablaPodcastsSeguidos.setItems(listaPodcastsSeguidos);
    }

    private void cargarVistaPodcasts() {
        mainView.setVisible(false);
        buscarArtistaUnico.setVisible(false);
        vistaPlaylistUnica.setVisible(false);
        vistaAlbumsGeneral.setVisible(false);
        vistaArtistasGeneral.setVisible(false);
        vistaAlbumUnico.setVisible(false);
        vistaRecentlyPlayed.setVisible(false);
        vistaYourPl.setVisible(false);
        vistaPlaylistsGeneral.setVisible(false);
        vistaCrearPlaylist.setVisible(false);
        vistaPlaylistsPatrocinadas.setVisible(false);
        vistaPodcasts.setVisible(true);
        vistaPodcastUnico.setVisible(false);
    }

    public void getCapitulosFollowedPodcast(MouseEvent mouseEvent) throws SQLException {
        String tituloPodcast = tablaPodcastsSeguidos.getSelectionModel().getSelectedItem().getTitulo();
        int idPodcast = tablaPodcastsSeguidos.getSelectionModel().getSelectedItem().getId();
        cargarVistaPodcastUnico(tituloPodcast, idPodcast);

    }


    public void getCapitulosAllPodcast(MouseEvent mouseEvent) throws SQLException {
        String tituloPodcast = tablaTodosPodcasts.getSelectionModel().getSelectedItem().getTitulo();
        int idPodcast = tablaTodosPodcasts.getSelectionModel().getSelectedItem().getId();
        cargarVistaPodcastUnico(tituloPodcast, idPodcast);
    }

    //PANE PODCAST UNICO

    private void cargarVistaPodcastUnico(String tituloPodcast, int idPodcast) throws SQLException {
        getvistaPodcastUnico();
        labelTituloPodcast.setText(tituloPodcast);
        listaCapitulosPodcastUnico.clear();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet query = st.executeQuery("SELECT * FROM capitulo" +
                " WHERE podcast_id = "+idPodcast+"");
        while (query.next()){
            int id1 = query.getInt("id");
            String titulo = query.getString("titulo");
            int duracion = query.getInt("duracion");
            Capitulo capitulo = new Capitulo(id1,titulo,duracion);
            listaCapitulosPodcastUnico.add(capitulo);
        }
        tablaPodcastUnico.setItems(listaCapitulosPodcastUnico);
    }

    private void getvistaPodcastUnico() {
        mainView.setVisible(false);
        buscarArtistaUnico.setVisible(false);
        vistaPlaylistUnica.setVisible(false);
        vistaAlbumsGeneral.setVisible(false);
        vistaArtistasGeneral.setVisible(false);
        vistaAlbumUnico.setVisible(false);
        vistaRecentlyPlayed.setVisible(false);
        vistaYourPl.setVisible(false);
        vistaPlaylistsGeneral.setVisible(false);
        vistaCrearPlaylist.setVisible(false);
        vistaPlaylistsPatrocinadas.setVisible(false);
        vistaPodcasts.setVisible(false);
        vistaPodcastUnico.setVisible(true);
    }

    public void reproducirCapituloPodcast(MouseEvent mouseEvent) {
        hBoxCancionInferior.setVisible(true);
        convertirCancionFav.setVisible(false);
        botonPlay.setVisible(false);
        botonPausa.setVisible(true);
        gifMusicaActiva.setVisible(true);
        String tituloCap = tablaPodcastUnico.getSelectionModel().getSelectedItem().getTitulo();
        tituloCancionInferior.setText(tituloCap);
        String duenyoPodcast = labelTituloPodcast.getText();
        nombreArtistaInferior.setText(duenyoPodcast);
    }

    // PODER SEGUIR Y DEJAR DE SEGUIR PODCASTS

    public void seguirPodcast(MouseEvent mouseEvent) throws SQLException {
        String nomPodc = labelTituloPodcast.getText();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT * FROM podcast" +
                " INNER JOIN podcast_usuario ON podcast.id = podcast_usuario.podcast_id" +
                " WHERE podcast_usuario.usuario_id = "+user.getId()+" AND podcast.titulo = '"+nomPodc+"'");
        if (resultSet.next()){
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Ya sigues a "+nomPodc);
            alert.showAndWait();
        }else{
            st.executeUpdate("INSERT into podcast_usuario VALUES ('"+user.getId()+"',(SELECT id from podcast WHERE titulo LIKE '"+nomPodc+"'))");
            Alert alert;
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Perfecto!");
            alert.setContentText("Has comenzado a seguir a "+nomPodc);
            alert.showAndWait();
        }
        cargarTablaPodcastsSeguidos();
    }

    public void dejarSeguirPodcast(MouseEvent mouseEvent) throws SQLException {
        String nomPodc = labelTituloPodcast.getText();
        Connection con = conexion();
        Statement st = con.createStatement();
        ResultSet query = st.executeQuery("SELECT * FROM podcast" +
                "  INNER JOIN podcast_usuario ON podcast.id = podcast_usuario.podcast_id" +
                "  WHERE podcast_usuario.usuario_id = "+user.getId()+" AND podcast.titulo = '"+nomPodc+"'");

        if (query.next()){
            st.executeUpdate("DELETE from podcast_usuario WHERE podcast_id =(SELECT id FROM podcast WHERE titulo = '"+nomPodc+"')");
            Alert alert;
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Perfecto!");
            alert.setContentText("Has dejado de seguir a "+nomPodc);
            alert.showAndWait();

        }else{
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No sigues a "+nomPodc);
            alert.showAndWait();
        }
        cargarTablaPodcastsSeguidos();
    }
}