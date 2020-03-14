package pl.lodz.p.pas.Library.converters;

import pl.lodz.p.pas.Library.controllers.rentalsRelated.RentalListController;
import pl.lodz.p.pas.Library.model.Resource;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "ProductConverter")
public class ProductConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        ValueExpression vex =
                facesContext.getApplication().getExpressionFactory()
                        .createValueExpression(facesContext.getELContext(),
                                "#{rentalListController}", RentalListController.class);

        RentalListController rentalListController = (RentalListController) vex.getValue(facesContext.getELContext());
        return rentalListController.getProductById(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        return ((Resource) object).getUUID();
    }
}
