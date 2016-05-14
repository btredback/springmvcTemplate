var esys = esys || {};
jQuery(document).ready(function() {
    jQuery(document).bind("contextmenu",
    function(e) {
        return true
    });
    jQuery('body').keydown(function(e) {
        if (e.keyCode == 8 && (e.target.readOnly == undefined || e.target.readOnly == true)) {
            e.preventDefault()
        }
        if (e.keyCode == 116 && top.F5) {
            top.F5()
        }
    });
    jQuery.ajaxSetup({
        cache: false
    })
});
function alert(a, b) {
    jQuery.messager.alert("提示", a, "info", b)
}
function confirm(b, c, d, e) {
    function _execFunc(a) {
        if (jQuery.isArray(d)) {
            a.apply(e, d)
        } else if (d) {
            a.call(e, d)
        } else {
            a.call(e)
        }
    }
    e = e || c;
    jQuery.messager.confirm("提示", b,
    function(a) {
        if (!c) return false;
        if (!a && c.onCancel) {
            _execFunc(c.onCancel);
            return false
        }
        if (a) {
            _execFunc(c.onOK ? c.onOK: c)
        }
    })
};
function confirmEx(a, b, c, d) {
    jQuery("iframe[xtype=mask]").remove();
    confirm(a, b, c, d);
    jQuery('<iframe src="about:blank" xtype=mask style="position:absolute; visibility:inherit; width:110%;height:100%;top:0px; left:0px;z-index:-1; filter:progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0);"></iframe>').appendTo(".messager-window")
}
function fnError(a, b, c) {
    alert("网络连接异常，请检查网络")
}
esys.confirm = function(a, b, c) {
    jQuery("iframe[xtype=mask]").remove();
    jQuery.messager.confirm(a, b, c);
    jQuery('<iframe src="about:blank" xtype=mask style="position:absolute; visibility:inherit; width:110%;height:100%;top:0px; left:0px;z-index:-1; filter:progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0);"></iframe>').appendTo(".messager-window")
};
function alertEx(a, b) {
    jQuery("iframe[xtype=mask]").remove();
    jQuery.messager.alert("提示", a, "info", b);
    jQuery('<iframe src="about:blank" xtype=mask style="position:absolute; visibility:inherit; width:110%;height:100%;top:0px; left:0px;z-index:-1; filter:progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0);"></iframe>').appendTo(".messager-window")
}
function prompt(b, c, d, e) {
    e = e || d;
    var b = b || "参数输入";
    var c = c || '输入参数值:';
    jQuery.messager.prompt(b, c,
    function(a) {
        if (arguments.length == 0) return false;
        if (!a || !d) {
            return false
        }
        d.call(e, a)
    })
}
function destroyTab(b) {
    function _destoryChildren(a) {
        for (var i = 0; i < a.length; i++) {
            if (a[i].frames && a[i].frames.length > 0) _destoryChildren(a[i].frames);
            a[i].location = 'about:blank'
        }
    }
    if (!frames[b]) return;
    try {
        if (frames[b].frames && frames[b].frames.length > 0) {
            _destoryChildren(frames[b].frames)
        }
        frames[b].location = 'about:blank'
    } catch(e) {}
}
function deleteLocalFiles(a) {
    var b = new ActiveXObject("Scripting.FileSystemObject");
    b.DeleteFile(a)
}
esys.request = function(d, e, f, g, h, i) {
    g = g || 'post';
    jQuery.ajax({
        url: d,
        dataType: 'json',
        cache: false,
        type: g,
        data: e,
        async: h !== false,
        success: function(a) {
            if (f) f(a)
        },
        error: function(a, b, c) {
            if (i !== false) alert("网络连接异常，请检查网络")
        }
    })
};
esys.get = function(a, b, c, d, e) {
    this.request(a, b, c, "get", d, e)
};
esys.getURLParameter = function(a, b) {
    return decodeURI((RegExp(a + '=' + '(.+?)(&|$)').exec(b || location.search) || [, null])[1])
};
var SCREEN_W_FIX = 20,
SCREEN_H_FIX = 40;
esys._dialogDimension = function(a, b) {
    var c = (window.screen.availWidth - a) / 2;
    var d = (window.screen.availHeight - b) / 2;
    if (a >= screen.availWidth) {
        a = screen.availWidth - (jQuery.browser.msie ? SCREEN_W_FIX: 0)
    }
    if (b >= screen.availHeight) {
        b = screen.availHeight - (jQuery.browser.msie ? SCREEN_H_FIX: 0)
    }
    d = d <= SCREEN_H_FIX / 2 ? 0 : d;
    c = c <= SCREEN_W_FIX / 2 ? 0 : c;
    return {
        "left": c,
        "top": d,
        "width": a,
        "height": b
    }
};
esys.addThemeParameter = function(a) {
    if (a.indexOf("theme=") == -1) {
        var b = null;
        if (jQuery("link[href*='easyui.css']").length == 1) {
            b = (/.*?\/(\w+)\/easyui.css/).exec(jQuery("link[href*='easyui.css']").attr("href"))[1]
        } else {
            b = esys.getURLParameter("theme", location.href) || window.theme
        }
        a = a + (a.indexOf("?") == -1 ? "?": "&") + "theme=" + b
    }
    return a
};
esys.openFullScreenWin = function(a, b) {
    var c = screen.availWidth - (jQuery.browser.msie ? SCREEN_W_FIX: 0);
    var d = screen.availHeight - (jQuery.browser.msie ? SCREEN_H_FIX: 0);
    var f = 0,
    left = 0;
    var g = b || new Date().getTime();
    var h = "height=" + d + ",width=" + c + ",top=" + f + ",left=" + left + ",scrollbars=no, resizable=yes,location=no, status=no";
    var i = /\?.+/;
    var j = null;
    a = esys.addThemeParameter(a);
    try {
        j = window.open("", g, h);
        if (j.location.href.replace(i, "").indexOf(a.replace(i, "")) === -1) {
            j = window.open(a, g, h)
        }
    } catch(e) {
        j = window.open(a, g, h)
    }
    j.focus();
    return j
};
esys.openWin = function(a, b, c, d, f) {
    var g = esys._dialogDimension(b, c);
    var h = g.left,
    top = g.top;
    b = g.width;
    c = g.height;
    var i = "height=" + c + ",width=" + b + ",top=" + top + ",left=" + h + ",scrollbars=no, resizable=" + (f === false ? "no": "yes") + ",location=no, status=no";
    try {
        a = esys.addThemeParameter(a);
        window.open(a, (d || new Date().getTime().toString()).replace(/\s+/gi, ''), i).focus();
    } catch(e) {
        alert("打开窗口错误:" + e.message + "<br/>" + i);
    }
};
esys.openModalDialog = function(a, b, c, d) {
    var e = esys._dialogDimension(b, c);
    var f = e.left,
    top = e.top;
    b = e.width;
    c = e.height;
    a = esys.addThemeParameter(a);
    return window.showModalDialog(a, d, "dialogWidth:" + b + "px;dialogHeight:" + c + "px;dialogTop:" + top + ";dialogLeft:" + f + ";scroll:no;status:no")
};
esys.disableBackspace = function() {
    jQuery(document).keydown(function(e) {
        var a = jQuery(event.target);
        var b = e.target.nodeName.toLowerCase();
        if (b != 'input' && b != 'textarea') {
            if (e.keyCode === 8) {
                return false
            }
        } else {
            var c = a.attr("readonly");
            var d = c != 'undefined' && c == 'readonly';
            var f = a.is(':disabled') || d;
            if (f) return false
        }
        return true
    })
};
esys.parseParam = function(s) {
    var c = {};
    if (!s) return c;
    var d = s.split(/,(?=\w+)/g);
    try {
        jQuery.each(d,
        function(i, a) {
            if (!a) return;
            try {
                var b = a.split("=");
                if (b && b[1]) {
                    c[$.trim(b[0])] = jQuery.trim(b[1])
                }
            } catch(e) {}
        })
    } catch(e) {
        c = {}
    }
    return c
};
esys.loadJs = function(a, b) {
    var c = false;
    var d = document.createElement('script');
    d.type = 'text/javascript';
    d.language = 'javascript';
    d.src = a;
    d.onload = d.onreadystatechange = function() {
        if (!c && (!d.readyState || d.readyState == 'loaded' || d.readyState == 'complete')) {
            c = true;
            d.onload = d.onreadystatechange = null;
            if (b) {
                b.call(d)
            }
        }
    };
    document.getElementsByTagName("head")[0].appendChild(d)
};
esys.loadCss = function(a, b) {
    var c = document.createElement('link');
    c.rel = 'stylesheet';
    c.type = 'text/css';
    c.media = 'screen';
    c.href = a;
    document.getElementsByTagName('head')[0].appendChild(c);
    if (b) {
        b.call(c)
    }
};
esys.browserIsIE = function() {
    if (jQuery.browser.msie) {
        return true
    } else {
        return false
    }
};
esys.calcuTimeDiff = function(a, b) {
    a = a || new Date();
    var c = null;
    try {
        var d = new Date() - new Date(b.replace(/-/g, '/'));
        c = Math.ceil(d / (24 * 60 * 60 * 1000))
    } catch(e) {}
    return c
};
esys.showMask = function(a, b, c) {
    c = c || {};
    if (b) {
        jQuery(a).mask({
            maskMsg: c.msg || '数据加载中...',
            opacity: c.opacity || 1
        })
    } else {
        jQuery(a).mask("hide")
    }
}; (function($) {
    $.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
        $.each(a,
        function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]]
                }
                o[this.name].push(this.value || '')
            } else {
                o[this.name] = this.value || ''
            }
        });
        return o
    };
    $.postJSON = function(a, b, c, d) {
        d = d !== false;
        return jQuery.ajax({
            'type': 'POST',
            'url': a,
            'async': d,
            'contentType': 'application/json',
            'data': JSON.stringify(b),
            'dataType': 'json',
            'success': c
        })
    }
})(jQuery); (function($) {
    function _doClick(a, b) {
        $.fn.popupWindow.defaultSettings = {
            height: 500,
            width: 500,
            name: null,
            url: null,
            fn: null
        };
        b = b || {
            name: "popwin",
            href: "about:blank"
        };
        $.popupWindow.defaultSettings = $.fn.popupWindow.defaultSettings;
        var c = $.extend({},
        $.fn.popupWindow.defaultSettings, a || {});
        if (c.fn) {
            c.fn.call(b)
        }
        c.name = c.name || b.name;
        c.url = c.url || b.href;
        esys.openWin(c.url, c.width, c.height, c.name);
        return false
    }
    $.fn.popupWindow = function(a) {
        return this.each(function() {
            $(this).click(function() {
                _doClick(a, this)
            })
        })
    };
    $.popupWindow = function(a) {
        _doClick(a)
    }
})(jQuery); (function($) {
    $.fn.extend({
        showTip: function(a) {
            a = $(this).attr("msg") || a;
            var b = $(this);
            var c = $("<div class=\"validatebox-tip\">" + "<span class=\"validatebox-tip-content\">" + "</span>" + "<span class=\"validatebox-tip-pointer\">" + "</span>" + "</div>").appendTo("body");
            c.find(".validatebox-tip-content").html(a);
            c.css({
                display: "block",
                left: b.offset().left + b.outerWidth(),
                top: b.offset().top
            });
            $(this)[0].tip = c;
            $(this).addClass("validatebox-invalid")
        },
        closeTip: function() {
            $(this).removeClass("validatebox-invalid");
            if (!$(this)[0] || !$(this)[0].tip) return;
            $(this)[0].tip.remove()
        }
    });
    var i = "<div id='{0}' title='双击立即关闭' class='loading-tip' style='display:none;overflow:hidden;'>" + "<b class='b1'></b><b class='b2'></b><b class='b3'></b><b class='b4'></b>" + "<div id='tipContent' class='content'>" + "<iframe  src='javascript:void(0);' style='position:absolute; visibility:inherit; width:100%;height:100%;top:0px; left:0px;z-index:-1; filter:progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0);'></iframe>" + "<div class='tip-icon'></div><div class='tip-text'></div></div>" + "<b class='b4'></b><b class='b3'></b><b class='b2'></b><b class='b1'></b>" + "</div>";
    var j = {
        _showTip: function(a, b, c, d, e, f) {
            c = c || false;
            d = d || 3;
            var g = this[0] ? $(this[0]) : $("body");
            f = f || "loading-tip";
            var h = $("#" + f, g);
            if (!h[0]) {
                g.append(i.replace(/\{0\}/gi, f));
                h = $("#" + f, g)
            }
            $("#tipContent", h).removeClass("loading").removeClass("success").removeClass("error");
            $("#tipContent", h).addClass(a).show();
            $(".tip-text", h).html(b);
            if (e) h.css(e);
            else h.css({
                left: '5px',
                bottom: '10px'
            });
            h.show();
            if (this._timer) {
                window.clearTimeout(this._timer)
            }
            if (c) {
                this._timer = setTimeout(function() {
                    j.closeTip(f)
                },
                d * 1000)
            } else {
                h.dblclick(function() {
                    j.closeTip(f)
                })
            }
        },
        hasShow: function(a) {
            var b = this[0] ? $(this[0]) : $("body");
            a = "#" + (a || "loading-tip");
            if ($(a, b)[0] && $(a, b).css("display") == "block") {
                return true
            } else {
                return false
            }
        },
        showError: function(a, b) {
            this._showTip("error", a, false, null, b)
        },
        showSuccess: function(a, b, c) {
            c = arguments.length == 2 ? true: c;
            this._showTip("success", a, c, 15, b)
        },
        showLoading: function(a, b) {
            this._showTip("loading", a, false, null, b)
        },
        showInfo: function(a, b, c, d) {
            this._showTip(c || "info", a, false, null, b, d)
        },
        closeTip: function(a) {
            a = "#" + (a || "loading-tip");
            var b = !this[0] ? $(a) : $(a, this[0]);
            if (b[0]) {
                b.hide()
            }
        }
    };
    $.fn.extend(j);
    $.extend(true, $.messager, j)
})(jQuery); (function($) {
    $.fn.extend({
        disable: function() {
            return this.each(function() {
                var a = $(this);
                a.attr("disabled", true);
                var b = a.data("events");
                if (!b || $.isEmptyObject(b)) {
                    return a
                }
                var c = new Array();
                for (var i in b) {
                    $.each(b[i],
                    function(j) {
                        c.push(i + "_" + j);
                        a.data("_E" + i + "_" + j, b[i][j].handler)
                    });
                    a.unbind(i)
                }
                a.attr("_E", c.join(","))
            })
        },
        enable: function() {
            return this.each(function() {
                var a = $(this);
                var b = a.attr("_E");
                if (!b) return a;
                var c = b.split(",");
                $.each(c,
                function(i) {
                    a.bind(c[i].replace(/_\d+$/, ""), a.data("_E" + c[i]));
                    a.removeData("_E" + c[i])
                });
                a.removeAttr("disabled").removeAttr("_E")
            })
        }
    })
})(jQuery);
Array.prototype.remove = function(i) {
    return this.splice(i, 1)[0]
};
Array.prototype.removeAll = function() {
    var o = this;
    while (this.length > 0) {
        o.pop()
    }
};
Array.prototype.clone = function() {
    var o = this;
    var a = [];
    for (var i = 0; i < o.length; i++) {
        a.push(o[i])
    }
    return a
};
Array.prototype.contain = function(a, b) {
    return this.indexOf(a, b) != -1
};
Array.prototype.get = function(a, b) {
    if (!a) return null;
    var c = this;
    var d = new Array;
    var e = typeof(a) == "string" ? a: a.value;
    var f = (typeof(a) == "object" && a.key) ? a.key: null;
    for (var i = 0; i < c.length; i++) {
        var v = f == null ? c[i] : c[i][f];
        if (v == e) {
            if (!b) {
                return c[i]
            } else {
                d.push(c[i])
            }
        }
    }
    return b ? d: null
};
Array.prototype.indexOf = function(a, b) {
    var c = this;
    for (var i = 0; i < c.length; i++) {
        if (!b) {
            if (c[i] == a) return i
        } else {
            if (c[i][b] == a[b]) return i
        }
    }
    return - 1
};
Array.prototype.swap = function(a, b) {
    var c = this;
    var d = c[b];
    c[b] = c[a];
    c[a] = d
};
Array.prototype.unique = function() {
    var n = {},
    r = [];
    for (var i = 0; i < this.length; i++) {
        if (!n[this[i]]) {
            n[this[i]] = true;
            r.push(this[i])
        }
    }
    return r
};
Array.prototype.getSameData = function() {
    var n = {},
    r = [],
    m = [];
    for (var i = 0; i < this.length; i++) {
        if (!n[this[i]]) {
            n[this[i]] = true;
            r.push(this[i])
        } else {
            m.push(this[i])
        }
    }
    return m
};
String.prototype.trim = function() {
    return Trim(this)
};
function LTrim(a) {
    var i;
    for (i = 0; i < a.length; i++) {
        if (a.charAt(i) != " " && a.charAt(i) != " ") break
    }
    a = a.substring(i, a.length);
    return a
}
function RTrim(a) {
    var i;
    for (i = a.length - 1; i >= 0; i--) {
        if (a.charAt(i) != " " && a.charAt(i) != " ") break
    }
    a = a.substring(0, i + 1);
    return a
}
function Trim(a) {
    return LTrim(RTrim(a))
}