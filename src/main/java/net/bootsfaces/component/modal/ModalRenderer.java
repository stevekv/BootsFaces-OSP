/**
 *  Copyright 2014-2017 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
 *
 *  This file is part of BootsFaces.
 *
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
 */

package net.bootsfaces.component.modal;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.CoreRenderer;

/** This class generates the HTML code of &lt;b:modal /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.modal.Modal")
public class ModalRenderer extends CoreRenderer {

	/**
	 * This methods generates the HTML code of the current b:modal.
	 * <code>encodeBegin</code> generates the start of the component. After the,
	 * the JSF framework calls <code>encodeChildren()</code> to generate the
	 * HTML code between the beginning and the end of the component. For
	 * instance, in the case of a panel component the content of the panel is
	 * generated by <code>encodeChildren()</code>. After that,
	 * <code>encodeEnd()</code> is called to generate the rest of the HTML code.
	 *
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:modal.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		if (!component.isRendered()) {
			return;
		}
		/*
		 * <div id="myModal" class="modal fade" tabindex="-1" role="dialog"
		 * aria-labelledby="myModalLabel" aria-hidden="true"> <div
		 * class="modal-dialog"> <div class="modal-content"> <div
		 * class="modal-header"> <button type="button" class="close"
		 * data-dismiss="modal" aria-hidden="true">×</button> <h3
		 * id="myModalLabel">Modal header</h3> </div> <div class="modal-body">
		 * <p>One fine body…</p> </div> <div class="modal-footer"> <button
		 * class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		 * <button class="btn btn-primary">Save changes</button> </div>
		 * </div><!-- /.modal-content --> </div><!-- /.modal-dialog -->
		 * </div><!-- /.modal -->
		 *
		 */

		ResponseWriter rw = context.getResponseWriter();

		Modal modal = (Modal) component;

		String title = modal.getTitle();
		rw.startElement("div", component); // modal
                String cid=component.getClientId(context);
		rw.writeAttribute("id", cid, "id");

		String styleClasses = "modal";
		if (modal.getStyleClass() != null) {
			styleClasses = modal.getStyleClass() + " " + styleClasses;
		}
		if (!modal.isBackdrop()) {
			rw.writeAttribute("data-backdrop", "static", null);
		}

		if (!modal.isCloseOnEscape()) {
			rw.writeAttribute("data-keyboard", "false", null);
		}

		rw.writeAttribute("class", styleClasses, "class");
		if (modal.getStyle() != null) {
			rw.writeAttribute("style", modal.getStyle(), "style");
		}
		rw.writeAttribute("role", "dialog", null);
		rw.writeAttribute("tabindex", "-1", null);
		rw.writeAttribute("aria-labelledby", cid + "_Label", null);
		rw.writeAttribute("aria-hidden", "true", null);

		rw.startElement("div", component); // modal-dialog
		String modalStyleClass = "modal" + "-dialog";
		if (modal.getSize() != null) {
			modalStyleClass = modalStyleClass + " " + modal.getSize();
		}
		rw.writeAttribute("class", modalStyleClass, "class");

		rw.startElement("div", component); // modal-content
		rw.writeAttribute("class", "modal-content", "class");

		rw.startElement("div", component); // modal-header
                rw.writeAttribute("id", cid+"_header", "id");

		String headerStyleClasses = "modal-header";
		if (modal.getHeaderClass() != null) {
			headerStyleClasses += " " + modal.getHeaderClass();
		}
		rw.writeAttribute("class", headerStyleClasses, "class");

		if (modal.getHeaderStyle() != null) {
			rw.writeAttribute("style", modal.getHeaderStyle(), "style");
		}


		if (modal.isClosable()) {
			rw.startElement("button", component);
			rw.writeAttribute("type", "button", "type");
			rw.writeAttribute("class", "close", "class");
			rw.writeAttribute("data-dismiss", "modal", "data-dismiss");
			rw.write("&times;");
			rw.endElement("button");
		}

		if (title != null) {
			rw.startElement("h4", component);
			rw.writeAttribute("id", cid + "_Label",
					"id");
			rw.writeText(title, null);
			rw.endElement("h4");
		}
		rw.endElement("div"); // modal-header

		rw.startElement("div", component); // modal-body
                rw.writeAttribute("id", cid+"_body", "id");
		if (modal.getContentClass() != null) {
			rw.writeAttribute("class", "modal-body " + modal.getContentClass(),
					"class");
		} else {
			rw.writeAttribute("class", "modal-body", "class");
		}

		if (modal.getContentStyle() != null) {
			rw.writeAttribute("style", modal.getContentStyle(), "style");
		}
	}

	/**
	 * This methods generates the HTML code of the current b:modal.
	 * <code>encodeBegin</code> generates the start of the component. After the,
	 * the JSF framework calls <code>encodeChildren()</code> to generate the
	 * HTML code between the beginning and the end of the component. For
	 * instance, in the case of a panel component the content of the panel is
	 * generated by <code>encodeChildren()</code>. After that,
	 * <code>encodeEnd()</code> is called to generate the rest of the HTML code.
	 *
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:modal.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		if (!component.isRendered()) {
			return;
		}
		ResponseWriter rw = context.getResponseWriter();

		rw.endElement("div"); // modal-body
                
                String cid=component.getClientId(context);

		UIComponent foot = component.getFacet("footer");
		if (foot != null) {
			rw.startElement("div", component); // modal-footer
                        rw.writeAttribute("id", cid+"_footer", "id");
			rw.writeAttribute("class", "modal-footer", "class");
			foot.encodeAll(context);

			rw.endElement("div"); // modal-footer
		}
		rw.endElement("div"); // modal-content
		rw.endElement("div"); // modal-dialog
		rw.endElement("div"); // modal
		initModal(rw, cid);
	}

	private void initModal(ResponseWriter rw, String cId) throws IOException {
		rw.startElement("script", null);
		rw.writeAttribute("id", cId.concat("_js"), null);
		rw.writeAttribute("type", "text/javascript", null);
		rw.write("$(function(){");
		rw.write("$('#CID').modal({ show: false });".replace("CID",
				escapeClientId(cId)));
		rw.write("});");
		rw.endElement("script");
	}

}
