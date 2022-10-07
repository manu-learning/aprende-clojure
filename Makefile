MKDIR=mkdir -p
COPY_OPTIONS= --exclude='project.clj' --exclude='HELP.md' \
	--include='*.md' --include='*.clj' \
	--include='*/' --exclude='*' \
	--prune-empty-dirs
COPY=rsync -vzr $(COPY_OPTIONS)

DIR_RELATIVE_ROOT = ./exercism-fun
DIR_EXERCISM_EXERCISES = $(DIR_RELATIVE_ROOT)/clojure
DIR_EXERCISES = $(DIR_RELATIVE_ROOT)/exercises

DIRS := $(dir $(wildcard $(DIR_EXERCISM_EXERCISES)/*/))

$(DIR_EXERCISM_EXERCISES):
	@$(MKDIR) $@

configure-exercism-workspace:
	$(info Configurando el workspace de Exercism para Clojure..)
	@exercism configure --workspace $(DIR_RELATIVE_ROOT)

copy-exercism-exercises: $(DIR_EXERCISM_EXERCISES)
	$(info Copiando ejercicios de la carpeta de Exercism al raíz)
	@$(foreach DIR, $(DIRS), \
		$(COPY) $(DIR) $(subst $(DIR_EXERCISM_EXERCISES),,$(DIR_EXERCISES)/$(DIR));\
	)

install-clojure:
	$(info Instalando Clojure + Clojure Tools..)
	@git clone git@github.com:practicalli/clojure-deps-edn.git ~/.clojure/

	@mkdir -p /tmp && cd /tmp && curl -O https://download.clojure.org/install/linux-install-1.10.3.967.sh && \
	chmod +x linux-install-1.10.3.967.sh && sudo ./linux-install-1.10.3.967.sh

	$(info Instalando paquetes de linux necesarios (rlwrap leinigen)..)
# para el historial de comandos, es interno de clj
	sudo apt install -y rlwrap
# facilita la creación de proyectos de clojure
	sudo apt install -y leiningen

.PHONY: copy-exercism-exercises install-clojure configure-exercism-workspace
