(ns libriot.layout.base
  (:require
    [hiccup
      [page :refer [html5 include-js include-css]]]))

(defn with-css []
  (list
   (include-css "/bootstrap/css/bootstrap.css")
   (include-css "/bootstrap/css/bootstrap-theme.css")
   (include-css "/css/dataTables.bootstrap.css")
   (include-css "/css/font-awesome.min.css")
   (include-css "/css/libriot.css")))

(defn with-js []
  (list
   (include-js "/js/jquery-1.10.2.min.js")
   (include-js "/bootstrap/js/bootstrap.js")
   (include-js "/js/jquery.raty.js")
   (include-js "/js/jquery.dataTables.js")
   (include-js "/js/dataTables.bootstrap.js")
   (include-js "/js/libriot.js")))

(defn in-modal [{:keys [clazz title body do-it cancel]}]
  (let [title-label (str clazz "-label")]
    [:div.modal.fade {:class (str "modal fade " clazz) :role "dialog" :aria-labelledby title-label :aria-hidden "true"}
     [:div.modal-dialog
      [:div.modal-content
       [:div.modal-header
        [:button.close {:type "button" :data-dismiss "modal" :aria-hidden "true"} "&times;"]
        [:h4.modal-title {:id title-label} title]]
       [:div.modal-body body]
       [:div.modal-footer
        [:button.btn.btn-default.cancel-btn {:type "button" :data-dismiss "modal"} cancel]
        [:button.btn.btn-warning.do-it-btn {:type "button"} do-it]]]]]))

(defn in-container [{:keys [container-class jumbo-class]} & content]
  (let [c [:div {"class" (str "container " container-class)} content]]
    (if jumbo-class
      [:div {:class (str "jumbotron " jumbo-class)} c]
      c)))

(defn with-bootstrap [title & content]
  (html5
    [:head
      [:title title]
      [:meta {"name" "viewport" "content" "width=device-width, initial-scale=1.0"}]
      (with-css)]
    [:body
      content
      (with-js)]))
