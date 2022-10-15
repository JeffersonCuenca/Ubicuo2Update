package pe.edu.upc.util;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import pe.edu.upc.entities.Alerta;

public class AlertaExporterPdf {
	
	private List<Alerta> listaAlertas;

	public AlertaExporterPdf(List<Alerta> listaAlertas) {
		super();
		this.listaAlertas = listaAlertas;
	}
	
	private void Cabecera(PdfPTable table) {
		PdfPCell celda = new PdfPCell();
	
		celda.setBackgroundColor(Color.black);
		celda.setPadding(4);
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.white);
		
		celda.setPhrase(new Phrase("ID", fuente));
		table.addCell(celda);
		
		celda.setPhrase(new Phrase("Estado", fuente));
		table.addCell(celda);
		
		celda.setPhrase(new Phrase("Usuario Auxiliado", fuente));
		table.addCell(celda);
		
		celda.setPhrase(new Phrase("Usuario Rescatista", fuente));
		table.addCell(celda);
		
		celda.setPhrase(new Phrase("Servicio", fuente));
		table.addCell(celda);
		
		celda.setPhrase(new Phrase("Fecha", fuente));
		table.addCell(celda);
		
	}
	
	@SuppressWarnings("deprecation")
	private void DatosTabla(PdfPTable table) {
		String espacio;
		espacio = " - ";
		
		for(Alerta alerta : listaAlertas) {
			table.addCell(String.valueOf(alerta.getIdAlerta()));
			table.addCell(alerta.getcEstados().getNameEstados());
			table.addCell(alerta.getIdUsuarioAux().getFistnameUsuario() + ' ' + alerta.getIdUsuarioAux().getLastnameUsuario());
			table.addCell(alerta.getIdUsuarioRes().getFistnameUsuario() + ' '  + alerta.getIdUsuarioRes().getLastnameUsuario() + espacio + alerta.getIdUsuarioRes().getCargo().getNameCargo());
			table.addCell(alerta.getIdUsuarioRes().getServicio().getNameServicio());
			table.addCell(alerta.getFecha().toLocaleString());
		}
	}
	
	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4.rotate());		
		PdfWriter.getInstance(documento, response.getOutputStream());
		
		documento.open();
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setColor(Color.black);
		fuente.setSize(18);
		
		Paragraph titulo = new Paragraph("Lista de Alertas", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);
		
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(90);
		table.setSpacingBefore(15);
		table.setWidths(new float[] {0.5f,1.5f,2f,3f,1.5f,2f});
		table.setWidthPercentage(100);
		
		Cabecera(table);
		DatosTabla(table);
		
		documento.add(table);
		documento.close();
	}
	
}
