# Port forwardings
#
OS="centos/7"

PORTS_N1={ 8180 => 80,
	   5156 => 5556,
	   4143 => 443,
	   9101 => 9001,
	   9102 => 9002,
	   9103 => 9003 }

PORTS_N2={ 8280 => 80,
	   5256 => 5556,
	   4243 => 443,
	   9201 => 9001,
	   9202 => 9002,
	   9203 => 9003 }

PORTS_N3={ 8380 => 80,
	   5356 => 5556,
	   4343 => 443,
	   9301 => 9001,
	   9302 => 9002,
	   9303 => 9003 }

SHELL_SCRIPT = <<-END
  yum update
  yum install -y tree
  echo "$(date)" > /provisioned_at
END

Vagrant.configure("2") do |config|


  #config.vm.synced_folder "vagrant-shared", "/vagrant-shared"

  config.vm.define "k8s-n1" do |n1|
    n1.vm.box = OS
    n1.vm.network "private_network", ip: "10.0.0.101"
    PORTS_N1.each do |port_host, port_guest|
      n1.vm.network "forwarded_port", guest: port_guest, host: port_host
    end
  end

  config.vm.define "k8s-n2" do |n2|
    n2.vm.box = OS
    n2.vm.network "private_network", ip: "10.0.0.102"
    PORTS_N2.each do |port_host, port_guest|
      n2.vm.network "forwarded_port", guest: port_guest, host: port_host
    end
  end

  config.vm.define "k8s-n3" do |n3|
    n3.vm.box = OS
    n3.vm.network "private_network", ip: "10.0.0.103"
    PORTS_N3.each do |port_host, port_guest|
      n3.vm.network "forwarded_port", guest: port_guest, host: port_host
    end
  end

  config.vm.provision "shell", inline: SHELL_SCRIPT

end
