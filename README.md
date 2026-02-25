<img src="SpaceInvaders.jpg" alt="Foto guapa guapa" width="1000" height="1000"> <br>
# COMO CLONAR EL REPO
git clone git@github.com:GonMa18/Space-Invaders.git

# COMO BAJARSE LA ÚLTIMA VERSION
git pull

# COMO SUBIR LAS VERSIONES 
git add .<br>
git commit -m "Nombre del commit"<br>
git push<br>

#
#
#
#COMO CLONAR EL REPO CON CLAVE SSH
ssh-keygen -t ed25519 -C "tu_email@dominio.com"<br>
eval "$(ssh-agent -s)"<br>
ssh-add ~/.ssh/id_ed25519<br>
clip < ~/.ssh/id_ed25519.pub<br>
Entrais a github - Click en la foto de perfil - Settings - SSH and GPG keys - New SSH key - CTRL + V y quitais del final el email.<br>
ssh -T git@github.com<br>
Este ultimo para cmprobar quer se ha conectado<br>
Ya podeis hacer git clone git@.... con el link del repo<br>
