package maquina1995.raider.io.profiler.controller;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;

import maquina1995.raider.io.profiler.dao.RaiderIoDao;
import maquina1995.raider.io.profiler.dto.Profile;
import maquina1995.raider.io.profiler.dto.RaidProgression;

/**
 * Controller
 * <p>
 * <a href="http://localhost:8080/datos-personaje">Ruta</a>
 * 
 * @author MaQuiNa1995
 */
@SpringComponent
@Route("/datos-personaje")
public class ProfileController extends VerticalLayout {

	private RaiderIoDao raiderIoDao;

	public ProfileController(RaiderIoDao raiderIoDao) {

		this.raiderIoDao = raiderIoDao;

		// --- Layout de introduccion de datos ---
		HorizontalLayout inputLayout = new HorizontalLayout();
		TextField regionTextField = new TextField("Region");
		TextField reinoTextField = new TextField("Reino");
		TextField nombreTextField = new TextField("Nombre");
		inputLayout.add(regionTextField, reinoTextField, nombreTextField);
		// Adicion a la layoutPrincipal
		add(inputLayout);

		// -- Boton de consulta ---
		Button consultarButton = new Button("Consultar");
		// Adicion a la layoutPrincipal
		add(consultarButton);

		// --- Layout de muestra de datos ---
		HorizontalLayout dataLayout = new HorizontalLayout();
		dataLayout.setVisible(false);

		// -- Izquierda
		Image retratoImagen = new Image();
		retratoImagen.setWidth("100px");

		// -- Centro
		VerticalLayout centerDataLayout = new VerticalLayout();

		// -- Centro -- Arrriba
		HorizontalLayout topDataLayout = new HorizontalLayout();
		NativeLabel razaLabel = new NativeLabel();
		NativeLabel claseLabel = new NativeLabel();
		NativeLabel specLabel = new NativeLabel();
		topDataLayout.add(razaLabel, claseLabel, specLabel);

		// -- Centro -- Abajo
		HorizontalLayout bottomDataLayout = new HorizontalLayout();
		NativeLabel sexoLabel = new NativeLabel();
		NativeLabel puntosLogrosLabel = new NativeLabel();
		bottomDataLayout.add(sexoLabel, puntosLogrosLabel);

		bottomDataLayout.getStyle()
				.set("margin", "0 auto");

		// Adicion a la layout del centro
		centerDataLayout.add(topDataLayout, bottomDataLayout);

		// -- Derecha
		Image faccionImagen = new Image();
		faccionImagen.setWidth("100px");

		// Adicion a la layout de muestra de datos ---
		dataLayout.add(retratoImagen, centerDataLayout, faccionImagen);

		// Adicion a la layoutPrincipal
		add(dataLayout);

		// Creacion del componente general de las pestañas
		TabSheet generalTabs = new TabSheet();
		generalTabs.setVisible(false);

		// Adicion de las pestañas al componente de las pestañas
		NativeLabel progresoPalacioNerubarLabel = new NativeLabel();
		generalTabs.add("Palacio Nerub'ar", progresoPalacioNerubarLabel);
		NativeLabel progresoProfundidadesRocaNegraLabel = new NativeLabel();
		generalTabs.add("Profundidades De Roca Negra", progresoProfundidadesRocaNegraLabel);
		NativeLabel progresoLiberacionMinahondaLabel = new NativeLabel();
		generalTabs.add("Liberación De Minahonda", progresoLiberacionMinahondaLabel);

		// Adicion a la layoutPrincipal
		add(generalTabs);

		// Listener del boton
		consultarButton.addClickListener(e -> {

			Profile response = this.raiderIoDao.characterInfoCall(regionTextField.getValue(), reinoTextField.getValue(),
					nombreTextField.getValue());

			// Rellenar datos
			retratoImagen.setSrc(response.getThumbnailUrl());
			razaLabel.setText(response.getRace());
			claseLabel.setText(response.getClazz());
			specLabel.setText(response.getActiveSpecName());
			String faction = response.getFaction();
			faccionImagen.setText(faction);
			switch (faction) {
			case "horde" -> faccionImagen
					.setSrc("https://wow.zamimg.com/images/wow/icons/large/ui_horde_honorboundmedal.jpg");
			case "alliance" -> faccionImagen
					.setSrc("https://wow.zamimg.com/images/wow/icons/large/ui_alliance_7legionmedal.jpg");
			}
			sexoLabel.setText(response.getGender());
			puntosLogrosLabel.setText(String.valueOf(response.getAchievementPoints()));

			// rellenar datos de pestaña
			RaidProgression raidProgression = response.getRaidProgression();
			progresoPalacioNerubarLabel.setText(raidProgression.getNerubarPalace()
					.getSummary());
			progresoProfundidadesRocaNegraLabel.setText(raidProgression.getBlackRockDepths()
					.getSummary());
			progresoLiberacionMinahondaLabel.setText(raidProgression.getLiberationOfUndermine()
					.getSummary());
			generalTabs.setSelectedIndex(0);

			dataLayout.setVisible(true);
			generalTabs.setVisible(true);
		});
	}
}
