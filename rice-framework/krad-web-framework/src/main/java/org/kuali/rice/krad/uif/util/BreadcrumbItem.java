/**
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.krad.uif.util;

import org.kuali.rice.krad.datadictionary.parse.BeanTag;
import org.kuali.rice.krad.datadictionary.parse.BeanTagAttribute;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.element.ContentElementBase;
import org.kuali.rice.krad.uif.lifecycle.ViewLifecycle;
import org.kuali.rice.krad.uif.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * BreadcrumbItem represents a single item in the breadcrumb list that is generated by the breadcrumbs widget.
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
@BeanTag(name = "breadcrumbItem-bean", parent = "Uif-BreadcrumbItem")
public class BreadcrumbItem extends ContentElementBase {
    private static final long serialVersionUID = 6694853722827812544L;

    private String label;
    private UrlInfo url;
    private Component siblingBreadcrumbComponent;
    private boolean renderAsLink;

    /**
     * The following updates are done here:
     *
     * <ul>
     * <li>Evaluate expressions on url object</li>
     * </ul>
     *
     * {@inheritDoc}
     */
    @Override
    public void performApplyModel(Object model, LifecycleElement parent) {
        super.performApplyModel(model, parent);

        View view = ViewLifecycle.getView();
        if (url != null) {
            Map<String, Object> context = new HashMap<String, Object>();

            Map<String, Object> viewContext = view.getContext();
            if (viewContext != null) {
                context.putAll(viewContext);
            }

            ViewLifecycle.getExpressionEvaluator().populatePropertyExpressionsFromGraph(url, false);
            ViewLifecycle.getExpressionEvaluator().evaluateExpressionsOnConfigurable(view, url, context);
        }
    }

    /**
     * The label for this breadcrumbItem.  The label is the textual content that will be displayed for the breadcrumb.
     *
     * @return the label
     */
    @BeanTagAttribute(name = "label")
    public String getLabel() {
        return label;
    }

    /**
     * Set the label for this breadcrumbItem.  The label is the textual content that will be displayed for the
     * breadcrumb.
     *
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * The url used for the breadcrumb link represented by this item
     *
     * @return the url object
     */
    @BeanTagAttribute(name = "url")
    public UrlInfo getUrl() {
        return url;
    }

    /**
     * Set the url object
     *
     * @param urlObject
     */
    public void setUrl(UrlInfo urlObject) {
        this.url = urlObject;
    }

    /**
     * Set the breadcrumb component for this breadcrumbs sibling content/navigation.  This content will appear in
     * a pop-up menu.
     *
     * @return the sibling component to appear in a popup menu
     */
    @BeanTagAttribute(name = "siblingBreadcrumbComponent", type = BeanTagAttribute.AttributeType.SINGLEBEAN)
    public Component getSiblingBreadcrumbComponent() {
        return siblingBreadcrumbComponent;
    }

    /**
     * Set the sibling breadcrumb component
     *
     * @param siblingBreadcrumbComponent
     */
    public void setSiblingBreadcrumbComponent(Component siblingBreadcrumbComponent) {
        this.siblingBreadcrumbComponent = siblingBreadcrumbComponent;
    }

    /**
     * If true, the breadcrumbItem will render as a link, otherwise it will render as a span (not-clickable).
     * By default, the last BreadcrumbItem in the list will ALWAYS render as span regardless of this property's value.
     *
     * @return true if rendering as a link, false otherwise
     */
    @BeanTagAttribute(name = "renderAsLink")
    public boolean isRenderAsLink() {
        return renderAsLink;
    }

    /**
     * Set to true to render as a link, false otherwise
     *
     * @param renderAsLink
     */
    public void setRenderAsLink(boolean renderAsLink) {
        this.renderAsLink = renderAsLink;
    }
}
