SUMMARY = "RTL8723 kernel driver (wifi + bluetooth)"
DESCRIPTION = "RTL8723 kernel driver"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://Kconfig;md5=ce4c7adf40ddcf6cfca7ee2b333165f0"

PV = "1.0-git"
SRCREV = "8534c0f3e042c03d6dd270994c6742bea3262913"
SRC_URI = "git://github.com/lwfinger/rtl8723bu.git;protocol=https \
           file://0001-ARM-Support.patch \
           file://0002-realtek-Disable-IPS-mode.patch "

S = "${WORKDIR}/git"

DEPENDS = "virtual/kernel"

inherit module

EXTRA_OEMAKE  = "ARCH=${ARCH} \
                 KSRC=${STAGING_KERNEL_BUILDDIR}"

do_compile () {
    oe_runmake
}

do_install () {
    install -d ${D}/lib/modules/${KERNEL_VERSION}
    install -m 0755 ${B}/8723bu.ko ${D}/lib/modules/${KERNEL_VERSION}/8723bu.ko

    install -d ${D}${sysconfdir}/modprobe.d
    echo "blacklist rtl8xxxu" > ${D}${sysconfdir}/modprobe.d/rtl8723-blacklist.conf
}

FILES_${PN} += "${sysconfdir}"
